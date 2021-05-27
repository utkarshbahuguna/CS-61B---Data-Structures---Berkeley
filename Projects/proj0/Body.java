public class Body {
    public double   xxPos,
                    yyPos,
                    xxVel,
                    yyVel,
                    mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    
    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    /** Calculates the distance between this body and body b. */
    public double calcDistance(Body b) {
        return Math.sqrt(Math.pow((b.xxPos - this.xxPos), 2) + Math.pow((b.yyPos - this.yyPos), 2));
    }

    /** Calculates the magnitude of force exerted by body b on this body. */
    public double calcForceExertedBy(Body b) {
        double G = 6.67e-11;
        if (this.equals(b)) {
            return 0;
        } else {
            return G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
        }
    }

    /** Calculates the x-axis component of the force exerted by body b on this body. */
    public double calcForceExertedByX(Body b) {
        return this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);

    }
    
    /** Calculates the y-axis component of the force exerted by body b on this body. */
    public double calcForceExertedByY(Body b) {
        return this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
    }

    /** Calculates the x-axis component of the net force exerted on this body by the array of bodies. */
    public double calcNetForceExertedByX(Body [] bodies) {
        double netForceX = 0;
        for (Body b : bodies) {
            if(!this.equals(b)) {
                netForceX += this.calcForceExertedByX(b);
            }
        }
        return netForceX;
    }        
    
    /** Calculates the y-axis component of the net force exerted on this body by the array of bodies. */
    public double calcNetForceExertedByY(Body [] bodies) {
        double netForceY = 0;
        for (Body b : bodies) {
            if(!this.equals(b)) {
                netForceY += this.calcForceExertedByY(b);
            }
        }
        return netForceY;
    }

    /** Updates the velocity and position of a body after a time interval dt,
      * with fX as the net force along x-axis and similar fY. */
    public void update(double dt, double fX, double fY) {
        double accelerationX = fX / this.mass;
        double accelerationY = fY / this.mass;
        this.xxVel += accelerationX * dt;
        this.yyVel += accelerationY * dt;
        this.xxPos += xxVel * dt;
        this.yyPos += yyVel * dt;
    }

    /** Draws itself using the StdDraw API. */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + this.imgFileName);
    }

}