package main.actor.dynamicactor;

import main.actor.staticactor.Chair;
import main.actor.staticactor.Computer;
import main.maps.Layer;
import main.maps.TiledMap;
import main.math.Vector2;
import main.actor.Actor;
import main.graphics.Renderer;
import main.graphics.Texture;
import main.utiles.Animation;
import main.utiles.Constants;

// les personnages du jeux ( etudiant + prof )
public abstract class Character extends Actor {

    protected float speed = Constants.CHARACTER_SPEED;       // le vitesse de deplacement

    protected TiledMap map;
    protected int characterSize = Constants.CHARACTER_SIZE;   // taille d'un personnage

    protected Vector2<Integer> goalPoint;       // position ou il doit se rendre
    protected boolean hasAGoal = false; // Si il doit aller queqlue part
    protected boolean isSit = false;    // Si il est assis
    protected boolean isOnTile = true; // Si il est pas entre deux tiles


    int dir = 0 ;               // La ou il regarde ( devant, derière, gauche ou droite)
    Animation animation;        // Animation de marche

    Computer computer = null;
    Chair chair = null;        // chaise ou il va s'asseoir

    //----------------------------------

    boolean canMoveLeft;
    boolean canMoveRight;
    boolean canMoveDown;
    boolean canMoveUp;
    int layer = Constants.LAYER_COLLISION;

    private boolean needToMoveX = true;

    protected Vector2<Float> clickPosition ;
<<<<<<< HEAD
=======

>>>>>>> Les ia sont bientot finis, ATTENTION BUG lorsqu'on clique sur en prof qui va vers un pc


    public Character(int x , int y, TiledMap map){
        super(x,y);
        this.map = map;
        texture = Texture.character;
        animation = new Animation(4 , 5 , true);

        clickPosition = new Vector2<>(( float)x  + Constants.CHARACTER_SIZE /2 , (float)y+ Constants.CHARACTER_SIZE /2 );


    }

    public void moveUp(){
        this.position.setY(this.position.getY()-speed);
    }
    public void moveDown(){
        this.position.setY(this.position.getY()+speed);
    }
    public void moveLeft(){
        this.position.setX(this.position.getX()-speed);
    }
    public void moveRight(){
        this.position.setX(this.position.getX()+speed);
    }
    public void moveTileUp(){
        if (this.position.getY()==((this.currentTile.getY()-speed)*Constants.TILE_SIZE)){
            this.currentTile.setY(this.currentTile.getY()-1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveUp();
        }
    }
    public void moveTileDown(){
        if (this.position.getY()==((this.currentTile.getY()+speed)*Constants.TILE_SIZE)){
            this.currentTile.setY(this.currentTile.getY()+1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveDown();
        }
    }
    public void moveTileLeft(){
        if (this.position.getX()==((this.currentTile.getX()-speed)*Constants.TILE_SIZE)) {
            this.currentTile.setX(this.currentTile.getX() - 1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveLeft();
        }
    }
    public void moveTileRight(){
        if (this.position.getX()==((this.currentTile.getX()+speed)*Constants.TILE_SIZE)){
            this.currentTile.setX(this.currentTile.getX()+1);
            this.isOnTile=true;
        } else {
            this.isOnTile=false;
            moveRight();
        }
    }


    private void defineCanMove(){
        canMoveLeft  = map.getLayer(layer).getGid((this.currentTile.getX()-1) + (this.currentTile.getY())  *Constants.HORIZONTAL_TILES ) == 0;
        canMoveRight = map.getLayer(layer).getGid((this.currentTile.getX()+1) + (this.currentTile.getY())  *Constants.HORIZONTAL_TILES ) == 0;

        canMoveDown =  map.getLayer(layer).getGid((this.currentTile.getX())   + (this.currentTile.getY()+1)*Constants.HORIZONTAL_TILES ) == 0;
        canMoveUp   =  map.getLayer(layer).getGid((this.currentTile.getX())   + (this.currentTile.getY()-1)*Constants.HORIZONTAL_TILES ) == 0;

        System.out.println(canMoveLeft + " : " + (this.currentTile.getX()-   1) +"," + this.currentTile.getY());
        //canMoveUp = false;
        //canMoveDown =false;
    }


    // Bouger sur l'axe X
    public void moveToX(int x, int y ){

        x = x*Constants.TILE_SIZE;
        y = y*Constants.TILE_SIZE;


       defineCanMove();

        if(this.position.getX() < x ){
            if(canMoveRight){
                dir = 2;
            } else if (canMoveUp){
                dir =3;
            }else {
                System.out.println("je suis perdu");
            }
        }else if ( x <this.position.getX()){
            if(canMoveLeft){
                dir = 1;
            }else if (canMoveUp){
                dir =3;
            }else {
                System.out.println("je suis perdu");
            }
        }
    }

    // Bouger sur l'axe Y
    public void moveToY(int y, int x ){

        defineCanMove();
        x = x*Constants.TILE_SIZE;
        y = y*Constants.TILE_SIZE;
        if(this.position.getY() < y ){
            if(canMoveDown){
                dir = 0;
            } else if(canMoveRight){
                dir = 2;
            } else if(canMoveLeft){
                dir =1;
            }else {
                System.out.println("je suis perdu");
            }

        }else if (this.position.getY() > y){
            if(canMoveUp){
                dir = 3;
            } else if(canMoveRight){
                dir =2;
            } else if(canMoveLeft){
                dir =1;
            }else {
                System.out.println("je suis perdu");
            }
        }
    }


    public void moveToGoalXY(){
        animation.update();
        animation.play();
        //System.out.println("Je suis le personnage " + id + " ( " + this.x + ", " + this.y + ")");
        //System.out.println("Je dois aller à "+ " ( " + goalPoint.getX() + ", " + goalPoint.getY() + ")");

        // On va d'abord en x


//        moveToX(goalPoint.getX(),goalPoint.getY());

//        if (this.position.getX() == goalPoint.getX()*Constants.TILE_SIZE){
//            // puis en  y
//            moveToY(goalPoint.getY(),goalPoint.getX());
//
//            if (this.position.getY() == goalPoint.getY()*Constants.TILE_SIZE){
//                hasAGoal = false;        // il est arrivé
//                System.out.println("Arrivé");
//                animation.pause();
//            }
//        }

<<<<<<< HEAD
=======

>>>>>>> Les ia sont bientot finis, ATTENTION BUG lorsqu'on clique sur en prof qui va vers un pc

        if (isOnTile){
            if (needToMoveX) {
                if (this.position.getX() == goalPoint.getX() * Constants.TILE_SIZE) {
                    System.out.println("Jai fini pour X");
                    needToMoveX = !needToMoveX;
                }else {
                    moveToX(goalPoint.getX(), goalPoint.getY());
                    isOnTile=false;
                }
            } else {
                if (this.position.getY() == goalPoint.getY() * Constants.TILE_SIZE) {
                    System.out.println("Jai fini pour Y");
                    needToMoveX = !needToMoveX;
                }else {
                    moveToY(goalPoint.getY(), goalPoint.getX());
                    isOnTile=false;
                }
            }

            System.out.println("JE vais en " + dir);


        }else {
            switch (dir){
                case 0 : moveTileDown();
                    break;
                case 1 : moveTileLeft();
                    break;
                case 2 : moveTileRight();
                    break;
                case 3 : moveTileUp();
                    break;

                default:
            }
        }

        if (this.position.getY() == goalPoint.getY() * Constants.TILE_SIZE
                    && this.position.getX() == goalPoint.getX() * Constants.TILE_SIZE) {
                hasAGoal = false;
            }

        //System.out.println(this.currentTile.getX() +"  "+ this.currentTile.getY());
        //System.out.println(isOnTile);

//        if (true) {
//
//
//            System.out.println("aller en X ? : " + needToMoveX);
//            if (needToMoveX) {
//                moveToX(goalPoint.getX(), goalPoint.getY());
//                if (this.position.getX() == goalPoint.getX() * Constants.TILE_SIZE) {
//                    needToMoveX = !needToMoveX;
//                }
//            } else {
//                moveToY(goalPoint.getY(), goalPoint.getX());
//                if (this.position.getY() == goalPoint.getY() * Constants.TILE_SIZE) {
//                    needToMoveX = !needToMoveX;
//                }
//            }
//
//            if (this.position.getY() == goalPoint.getY() * Constants.TILE_SIZE
//                    && this.position.getX() == goalPoint.getX() * Constants.TILE_SIZE) {
//                hasAGoal = false;
//            }
//        }

    }


    //------------------ Protected Methodes ------------------

    protected void goalManagement(){
        if (hasAGoal){
            moveToGoalXY();
        }
    }


    protected void renderCharacter(float[] color){
        texture.bind();
        Renderer.renderActor(this.position.getX() ,this.position.getY(), characterSize, characterSize, color, 4.0f , animation.getCurrentFrame(), dir);
        texture.unbind();
    }



//    protected void keyManagement(){
//        if (Component.input.isKeyDown(GLFW_KEY_Z ) || Component.input.isKeyDown(GLFW_KEY_W) ){
//            dir = 3 ;
//            y--;
//            //System.out.println("Il avance on dirait");
//            animation.play();
//            animation.update();
//        }if (Component.input.isKeyDown(GLFW_KEY_S )){
//            dir = 0 ;
//            y++;
//            animation.play();
//            animation.update();
//
//        }if (Component.input.isKeyDown(GLFW_KEY_Q ) || Component.input.isKeyDown(GLFW_KEY_A) ){
//            dir = 1;
//            x--;
//            animation.play();
//            animation.update();
//
//        }if (Component.input.isKeyDown(GLFW_KEY_D )){
//            dir = 2;
//            x++;
//            animation.play();
//            animation.update();
//
//        }
//    }

    //-------------------------


    public Vector2<Integer> getGoalPoint() {
        return goalPoint;
    }

    public void setGoalPoint(Vector2<Integer> goalPoint) {
        this.goalPoint = goalPoint;
    }

    public boolean isHasAGoal() {
        return hasAGoal;
    }

    public void setHasAGoal(boolean hasAGoal) {
        this.hasAGoal = hasAGoal;
    }

    public Chair getChair() {
        return chair;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public boolean isSit() {
        return isSit;
    }

    public void setSit(boolean sit) {
        isSit = sit;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public Vector2<Float> getClickPosition() {
        return clickPosition;
    }
}
