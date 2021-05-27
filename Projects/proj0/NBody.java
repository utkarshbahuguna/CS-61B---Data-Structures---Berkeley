/** Runs the simulation. */
public class NBody {
    
    /** Returns the radius of the system given in file. */
    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readInt();
        return in.readDouble();
    }

    /** Returns an array of body objects with information given in file. */
    public static Body[] readBodies(String filename) {
        In in = new In(filename);
        int numBodies = in.readInt();

        Body[] bodies = new Body[numBodies];
        
        in.readDouble();
        
        for(int i = 0; i < numBodies; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
        }
        return bodies;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        Body[] bodies = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);
        int numBodies = bodies.length;

        String background = "images/starfield.jpg";

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        
        double time = 0;
        int waitTimeMilliseconds = 10;
        
        while (time <= T) {
            double[] xForces = new double[numBodies];
            double[] yForces = new double[numBodies];
            
            /** Calculate and store net forces on every body. */
            for (int i = 0; i < numBodies; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            
            /** Update each body's position and velocity. */
            for (int i = 0; i < numBodies; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            /** Draws one frame of the animation. */
            StdDraw.picture(0, 0, background);
            for (Body b: bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(waitTimeMilliseconds);

            time += dt;
        }

        // Print the final state of the bodies in the system.
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                            bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                            bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}