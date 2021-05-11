package com.zenithgames.shadowrunner.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.zenithgames.shadowrunner.actors.Background;
import com.zenithgames.shadowrunner.actors.Button;
import com.zenithgames.shadowrunner.actors.Ground;
import com.zenithgames.shadowrunner.actors.Orb;
import com.zenithgames.shadowrunner.actors.Runner;
import com.zenithgames.shadowrunner.box2d.OrbUserData;
import com.zenithgames.shadowrunner.box2d.UserData;
import com.zenithgames.shadowrunner.utils.AssetsLoader;
import com.zenithgames.shadowrunner.utils.BodyUtils;
import com.zenithgames.shadowrunner.utils.WorldUtils;


public abstract class GameStage extends Stage implements ContactListener {
    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;

    private Array<ParticleEffect> constantParticleEffectArray;
    private Array<ParticleEffect> explosionParticleEffectArray;
    private Array<Ground> groundArray;


    protected boolean debug = false;

    private Button left,right,up;
    protected World world;
    protected Runner runner;
    protected Background background;
    private int orbNum;


    private float r,b,g;

    private int score;
    private float accumulator = 0f;

    private Array<Button> touchButtonArray;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;


    //private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Vector3 touchPoint;



    public GameStage() {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        
        score = 0;
        orbNum = 0;
        constantParticleEffectArray = new Array<>();
        explosionParticleEffectArray = new Array<>();
        groundArray = new Array<>();


        touchButtonArray = new Array<>();
        for(int i =0;i<10;i++) {
            touchButtonArray.add(left);
        }


        setUpWorld();
        setupCamera();
        setupButtons();
        setupScenario();
        setupTouchControlAreas();

        renderer = new Box2DDebugRenderer();
    }

    private void setupButtons(){
        left = new Button(0, 0, VIEWPORT_WIDTH/5, VIEWPORT_HEIGHT);
        right = new Button(VIEWPORT_WIDTH/5, 0, VIEWPORT_WIDTH/5, VIEWPORT_HEIGHT);
        up = new Button(2*VIEWPORT_WIDTH/5, 0, 3*VIEWPORT_WIDTH/5, VIEWPORT_HEIGHT);

    }

    public void reset(){
        this.clear();


        score = 0;
        orbNum = 0;

        constantParticleEffectArray = new Array<>();
        explosionParticleEffectArray = new Array<>();
        groundArray = new Array<>();

        setUpWorld();
        setupScenario();


    }


    public void addOrb(){
        orbNum++;
    }

    public void removeOrb(){
        orbNum--;
    }

    public abstract void setupScenario();


    private void setUpWorld() {
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        if(runner!=null){
            runner.resetFlip();
        }
        setUpRunner();
        setUpOrbs();


    }

    protected abstract void setUpOrbs();


    protected abstract void setUpRunner();

    public void addConstantParticleEffect(ParticleEffect p){
        constantParticleEffectArray.add(p);
    }
    public void removeConstantParticleEffect(ParticleEffect p){
        constantParticleEffectArray.removeValue(p, true);
    }

    public void addExplosionParticleEffect(ParticleEffect p){
        explosionParticleEffectArray.add(p);
    }

    private void setupCamera() {
        //camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera = (OrthographicCamera)this.getCamera();
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    protected abstract void setUpBackground();


    private void setupTouchControlAreas() {
        touchPoint = new Vector3();

        Gdx.input.setInputProcessor(this);
    }




    public void update() {

        //TODO: if runner kanpoan o tranpa: runner = dead


        if (runner.getY() + runner.getHeight() / 2 < 0) {
            Gdx.app.log("Stage", "Game Over");
            reset();
        }


        if(orbNum==0){
            Gdx.app.log("Stage","Level cleared");
        }
        sweepDeadBodies();


    }




    @Override
    public void act(float delta) {
        super.act(delta);
        update();


        // Fixed timestep
        accumulator += delta;

        while (accumulator >= delta) {
            float TIME_STEP = 1 / 300f;
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }


        //TODO: Implement interpolation

    }

    protected abstract void drawText(Batch b);


    @Override
    public void draw() {
        super.draw();

        for(int i=0;i<constantParticleEffectArray.size;i++){
            constantParticleEffectArray.get(i).update(Gdx.graphics.getDeltaTime());
        }
        for(int i=0;i<explosionParticleEffectArray.size;i++){
            explosionParticleEffectArray.get(i).update(Gdx.graphics.getDeltaTime());
        }
        Gdx.gl.glEnable(GL20.GL_BLEND);

        Batch batch = getBatch();


        batch.enableBlending();
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawText(batch);


        for(int i=0;i<groundArray.size;i++){
            groundArray.get(i).draw(batch,1);
        }


        enableTransparency(batch);
        drawButtons(batch);



        for(int i=0;i<constantParticleEffectArray.size;i++){
            constantParticleEffectArray.get(i).draw(batch);
            constantParticleEffectArray.get(i).getEmitters().first().durationTimer=0;
        }
        for(int i=0;i<explosionParticleEffectArray.size;i++){
            explosionParticleEffectArray.get(i).draw(batch);
        }



        runner.draw(batch,1.0f);
        disableTransparency(batch);

        batch.end();

        for(int i=0;i<constantParticleEffectArray.size;i++){
            if (constantParticleEffectArray.get(i).isComplete()){
                constantParticleEffectArray.get(i).reset();
                //particleEffectArray.removeIndex(i);
            }
        }

        for(int i=0;i<explosionParticleEffectArray.size;i++){
            if (explosionParticleEffectArray.get(i).isComplete()){
                explosionParticleEffectArray.removeIndex(i);
            }
        }


        if(debug){
            renderer.render(world, camera.combined);
        }
    }



    public void drawButtons(Batch batch){



        batch.draw(AssetsLoader.black, left.x+left.width/4, left.y, 2, 2);
        batch.draw(AssetsLoader.black, right.x+right.width/4, right.y, 2, 2);
        //batch.draw(AssetsLoader.black, up.x, up.y, 2, 2);




    }

    public void enableTransparency(Batch batch){
        batch.enableBlending();
        r=batch.getColor().r;
        g=batch.getColor().g;
        b= batch.getColor().b;
        batch.setColor(r, g, b, 0.5f);
    }

    public void disableTransparency(Batch batch){
        batch.disableBlending();
        batch.setColor(r, g, b, 1f);
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if(pointer>10) return false;

        // Need to get the actual coordinates
        translateScreenToWorldCoordinates(x, y);

        if (rightTouched(touchPoint.x, touchPoint.y)) {
            touchButtonArray.set(pointer,right);
            right.setPressed();
            left.setNotPressed();
            runner.goRight();
        } else if (leftTouched(touchPoint.x, touchPoint.y)) {
            touchButtonArray.set(pointer,left);
            left.setPressed();
            right.setNotPressed();
            runner.goLeft();
        } else if(upTouched(touchPoint.x,touchPoint.y)){
            touchButtonArray.set(pointer,up);
            up.setPressed();
            runner.jump();
        }

        return super.touchDown(x, y, pointer, button);
    }




    private boolean rightTouched(float x, float y) {
        return right.contains(x, y);
    }
    private boolean leftTouched(float x, float y) {
        return left.contains(x,y);
    }
    private boolean upTouched(float x, float y) {
        return up.contains(x, y);
    }



    public boolean touchUp(int x, int y, int pointer, int button) {
        if(pointer>10) return false;
        Button myButton = touchButtonArray.get(pointer);
        if(myButton==null) return false;
        myButton.setNotPressed();
        if(myButton == left || myButton == right){
            runner.stop();
        }
        if(myButton == up){
            runner.markToFall();
        }

        return super.touchDown(x, y, pointer, button);
    }


    public boolean touchDragged(int screenX,
                                int screenY,
                                int pointer){


        translateScreenToWorldCoordinates(screenX,screenY);

        if(pointer>10) return false;
        Button myButton = touchButtonArray.get(pointer);
        if(myButton==null) return false;

        if(myButton == up){
            return super.touchDragged(screenX, screenY, pointer);
        }

        touchUp(0,0,pointer,0);
        touchDown(screenX, screenY, pointer, 0);
        return super.touchDragged(screenX, screenY, pointer);
    }





    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }


    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if(BodyUtils.bodyIsRunner(b)){
            Body help = a;
            a=b;
            b=help;
        }

        /*
        if (BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)){
            runner.landed();
        }
        */

        if(BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsOrb(b)){

            OrbUserData oud=(OrbUserData)b.getUserData();
            Orb orb=(Orb)oud.getActor();
            orb.explode();
            runner.changeColor();
            Gdx.app.log("Orb","Explode!");
        }

        Gdx.app.log("contact", "started");

    }
    public void sweepDeadBodies() {
        Array<Body> bodies = new Array<>();
        world.getBodies(bodies);

        for (Body body: bodies) {
            if (body != null) {
                UserData data = (UserData) body.getUserData();
                if (data.isFlaggedToRemove()) {
                    data.getActor().remove(world);
                }
            }
        }
    }

    public void endContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if(BodyUtils.bodyIsRunner(b)){
            Body help = a;
            a=b;
            b=help;
        }


        if (BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) {
            /*
            if(runner.isFalling()){
                runner.setFalling();
            }
            */
        }
        Gdx.app.log("contact","ended");
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }







}
