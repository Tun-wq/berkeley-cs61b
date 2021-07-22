public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
    	this.xxPos = xP;
    	this.yyPos = yP;
    	this.xxVel = xV;
    	this.yyVel = yV;
    	this.mass = m;
    	this.imgFileName = img;
    }
    public Planet(Planet p){
    	this.xxPos = p.xxPos;
    	this.yyPos = p.yyPos;
    	this.xxVel = p.xxVel;
    	this.yyVel = p.yyVel;
    	this.mass = p.mass;
    	this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
    	double dis = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
    	return Math.sqrt(dis);
    }

    public double calcForceExertedBy(Planet p){
    	return G * p.mass * this.mass / Math.pow(calcDistance(p), 2);
    }

    public double calcForceExertedByX(Planet p){
    	double dx = p.xxPos - this.xxPos;
    	return calcForceExertedBy(p) * dx / calcDistance(p);
    }
    
    public double calcForceExertedByY(Planet p){
    	double dy = p.yyPos - this.yyPos;
    	return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allplanets){
    	double sumX = 0;
    	for(int i = 0; i < allplanets.length; i++){
    		if(!this.equals(allplanets[i])){
    			sumX += calcForceExertedByX(allplanets[i]);
    		}
    	}
    	return sumX;
    }

    public double calcNetForceExertedByY(Planet[] allplanets){
    	double sumY = 0;
    	for(int i = 0; i < allplanets.length; i++){
    		if(!this.equals(allplanets[i])){
    			sumY += calcForceExertedByY(allplanets[i]);
    		}
    	}
    	return sumY;
    }

    public void update(double dt, double fX, double fY){
    	double aX = fX / mass;
    	double aY = fY / mass;
    	xxVel = xxVel + dt * aX;
    	yyVel = yyVel + dt * aY;
    	xxPos = xxPos + dt * xxVel;
    	yyPos = yyPos + dt * yyVel; 
    }

    public void draw(){
    	String filename = "images/" + imgFileName;
    	StdDraw.picture(xxPos, yyPos, filename);
    }
}