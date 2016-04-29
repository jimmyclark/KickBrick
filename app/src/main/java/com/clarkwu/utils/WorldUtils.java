package com.clarkwu.utils;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.ContactListener;
import org.jbox2d.dynamics.World;

/**
 * Created by Administrator on 2016/4/20.
 */
public class WorldUtils{
    private static WorldUtils instance;
    private World world;

    public static WorldUtils getInstance(){
        if(instance ==  null){
            instance = new WorldUtils();
        }
        return instance;
    }

    public static final float WORLD_RATE = 30;
    private static final int WORLD_GRAVITY = 0;

    public void setInitWorld(){
        AABB aabb = new AABB();
        aabb.lowerBound.set(-100,-100);
        aabb.upperBound.set(100,100);
        Vec2 gravity = new Vec2(0,WORLD_GRAVITY);
        world = new World(aabb,gravity,true);
    }

    public void destroyBody(Body body){
            body.setUserData(null);
    }

    public Body createWorldPolygon(float x,float y, float width,float height,boolean isStatic,
                                   float friction,float restitution){
        PolygonDef pd = new PolygonDef();
        if(isStatic){
            pd.density = 0;
        }else{
            pd.density = 1;
        }
        pd.friction = friction;
        pd.restitution = restitution;
        pd.setAsBox(width / 2 / WORLD_RATE, height / 2 / WORLD_RATE);

        BodyDef bd = new BodyDef();
        bd.position.set((x + width / 2) / WORLD_RATE, (y + height / 2) / WORLD_RATE);
        Body body = world.createBody(bd);
        body.m_userData = "rect";
        if(body != null) {
            try{
                body.createShape(pd);
                body.setMassFromShapes();
            }catch (Exception e){
            }
        }
        return body;
    }

    public Body createCircle(float x,float y,float r, boolean isStatic,
                             float friction,float restitution){
        CircleDef cd = new CircleDef();
        if (isStatic) {
            cd.density = 0;
        } else {
            cd.density = 1;
        }
        cd.friction = friction;
        cd.restitution = restitution;
        cd.radius = r / WORLD_RATE;
        BodyDef bd = new BodyDef();
        bd.position.set((x + r) / WORLD_RATE, (y + r) / WORLD_RATE);
        Body body = world.createBody(bd);
        body.m_userData = "circle";
        body.createShape(cd);
        body.setMassFromShapes();
        return body;
    }

    public void logic(){
        world.step(1f/60f,10);
        Body body = world.getBodyList();
        while(body!= null){
            if(body.getUserData() == null){
                world.destroyBody(body);
            }
            body = body.getNext();
        }
    }

    public Vec2 getWorldPosition(Body body){
        return body.getPosition();
    }

    public void setContactListener(ContactListener listener){
        world.setContactListener(listener);
    }
}
