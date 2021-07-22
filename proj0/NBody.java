public class NBody{
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int numbers = in.readInt();
		double radius = in.readDouble();
		return radius;  
	}

	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int num = in.readInt();
		in.readDouble();
		Planet[] allplanets = new Planet[num];
		int index = 0;
		while(index < num){
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			allplanets[index] =new Planet(xP, yP, xV, yV, m, img);
			index += 1;
		}
		return allplanets;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, "images/starfield.jpg");

		Planet[] allplanets = readPlanets(filename);
		for(int i = 0; i < allplanets.length; i++){
			allplanets[i].draw();
		}

		StdDraw.enableDoubleBuffering();

		double t = 0;
		while(t != T){
			double[] xForces = new double[allplanets.length];
			double[] yForces = new double[allplanets.length];
			for(int i = 0; i < allplanets.length; i++){
				xForces[i] = allplanets[i].calcNetForceExertedByX(allplanets);
				yForces[i] = allplanets[i].calcNetForceExertedByY(allplanets);
			}
			for(int i = 0; i < allplanets.length; i++){
				allplanets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int i = 0; i < allplanets.length; i++){
				allplanets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
	}
}