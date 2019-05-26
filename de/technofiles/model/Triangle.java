package de.technofiles.model;

public class Triangle extends Shape {
    Coordinates coordA;
    Coordinates coordB;
    Coordinates coordC;
    double a = 0.0;
    double b = 0.0;
    double c = 0.0;
    double alpha = 0.0;
    double beta = 0.0;
    double gamma = 0.0;

    // Construct if you have lengths of all sides
    public Triangle(double a, double b, double c) {
	setA(a);
	setB(b);
	setC(c);
	// Results of deviation can be shown online:
	// https://www.matheretter.de/rechner/geozeichner/
	this.coordA = new Coordinates(0.0, 0.0);
	this.coordB = deviation(coordA, c, 90.0);
	this.coordC = deviation(coordB, a, getBeta() - 90.0);
    }

    // Construct if you have coordinates of all edges
    public Triangle(Coordinates a, Coordinates b, Coordinates c) {
	this.coordA = a; // between b and c
	this.coordB = b; // between a and c
	this.coordC = c; // between a and b
	setA(distance(b, c));
	setB(distance(a, c));
	setC(distance(a, b));
    }

    /**
     * return information
     */
    @Override
    public String toString() {
	return "Triangle with\nA at " + this.coordA.toString() + "\nB at " + this.coordB.toString() + "\nC at "
		+ this.coordC.toString() + "\na: " + round(this.getA(), POSITIONS) + " b: "
		+ round(this.getB(), POSITIONS) + " c: " + round(this.getC(), POSITIONS) + "\nAlpha: "
		+ round(this.getAlpha(), POSITIONS) + "\nBeta: " + round(this.getBeta(), POSITIONS) + "\nGamma: "
		+ round(this.getGamma(), POSITIONS) + "\nHeight A: " + round(this.getHeightA(), POSITIONS)
		+ "\nHeight B: " + round(this.getHeightB(), POSITIONS) + "\nHeight C: "
		+ round(this.getHeightC(), POSITIONS) + "\nArea: " + round(this.getArea(), POSITIONS)
		+ "\nCircumference: " + round(this.getCircumference(), POSITIONS);
    }

    /**
     * @return the calculated height of A
     */
    public double getHeightA() {
	if (getC() != 0.0 && getBeta() != 0.0) {
	    return c * Math.sin(Math.toRadians(beta));
	}
	if (getB() != 0.0 && getGamma() != 0.0) {
	    return b * Math.sin(Math.toRadians(gamma));
	}
	return 0.0;
    }

    /**
     * @return the calculated height of B
     */
    public double getHeightB() {
	if (getA() != 0.0 && getGamma() != 0.0) {
	    return a * Math.sin(Math.toRadians(gamma));
	}
	if (getC() != 0.0 && getAlpha() != 0.0) {
	    return c * Math.sin(Math.toRadians(alpha));
	}
	return 0.0;
    }

    /**
     * @return the calculated height of C
     */
    public double getHeightC() {
	if (getB() != 0.0 && getAlpha() != 0.0) {
	    return b * Math.sin(Math.toRadians(alpha));
	}
	if (getA() != 0.0 && getBeta() != 0.0) {
	    return a * Math.sin(Math.toRadians(beta));
	}
	return 0.0;
    }

    public double getA() {
	// Try to calculate a if it is 0.0
	if (a == 0.0) {
	    if (alpha != 0.0 && beta != 0.0 && b != 0.0) {
		// a/sin(alpha) = b/sin(beta) => a = sin(alpha)*b/sin(beta)
		setA(Math.sin(alpha) * b / Math.sin(beta));
	    } else if (alpha != 0.0 && gamma != 0.0 && c != 0.0) {
		// a/sin(alpha) = c/sin(gamma) => a = sin(alpha)*c/sin(gamma)
		setA(Math.sin(alpha) * c / Math.sin(gamma));
	    } else if (alpha != 0.0 && b != 0.0 && c != 0.0) {
		// Kosinussatz a²=b²+c²-2b*c*cos(alpha)
		setA(Math.sqrt(b * b + c * c - 2 * b * c * Math.cos(Math.toRadians(alpha))));
	    }
	    if (Double.isNaN(a)) {
		a = 0.0;
	    }
	}
	return a;
    }

    public void setA(double a) {
	this.a = a;
    }

    public double getAlpha() {
	// Try to calculate alpha if it is 0.0
	if (alpha == 0.0) {
	    if (beta != 0.0 && gamma != 0.0) {
		// Die Winkelsumme muss 180 sein
		setAlpha(180.0 - (beta + gamma));
	    } else if (a != 0.0 && b != 0.0 && beta != 0.0) {
		// a/sin(alpha) = b/sin(beta) => alpha = arcsin(a*sin(beta)/b)
		setAlpha(Math.toDegrees(Math.asin(a * Math.sin(Math.toRadians(beta)) / b)));
	    } else if (a != 0.0 && c != 0.0 && gamma != 0.0) {
		// a/sin(alpha) = c/sin(gamma) => alpha = arcsin(a*sin(gamma)/c)
		setAlpha(Math.toDegrees(Math.asin(a * Math.sin(Math.toRadians(gamma)) / c)));
	    } else if (a != 0.0 && b != 0.0 && c != 0.0) {
		// Kosinussatz a²=b²+c²-2bc*cos(alpha) => alpha = arccos((b²+c²-a²)/2bc))
		setAlpha(Math.toDegrees(Math.acos((b * b + c * c - a * a) / (2 * b * c))));
	    }
	    if (Double.isNaN(alpha)) {
		alpha = 0.0;
	    }
	}
	return alpha;
    }

    public void setAlpha(double alpha) {
	this.alpha = alpha;
    }

    public double getB() {
	// Try to calculate b if it is 0.0
	if (b == 0.0) {
	    if (alpha != 0.0 && beta != 0.0 && a != 0.0) {
		// b/sin(beta) = a/sin(alpha) => b = sin(beta)*a/sin(alpha)
		setB(Math.sin(beta) * a / Math.sin(alpha));
	    } else if (beta != 0.0 && gamma != 0.0 && c != 0.0) {
		// b/sin(beta) = c/sin(gamma) => b = sin(beta)*c/sin(gamma)
		setB(Math.sin(beta) * c / Math.sin(gamma));
	    } else if (beta != 0.0 && a != 0.0 && c != 0.0) {
		// Kosinussatz b²=a²+c²-2ac*cos(beta)
		setA(Math.sqrt(a * a + c * c - 2 * a * c * Math.cos(Math.toRadians(beta))));
	    }
	    if (Double.isNaN(b)) {
		b = 0.0;
	    }
	}
	return b;
    }

    public void setB(double b) {
	this.b = b;
    }

    public double getBeta() {
	// Try to calculate beta if it is 0.0
	if (beta == 0.0) {
	    if (alpha != 0.0 && gamma != 0.0) {
		// Die Winkelsumme muss 180 sein
		setBeta(180.0 - (alpha + gamma));
	    } else if (a != 0.0 && b != 0.0 && alpha != 0.0) {
		// a/sin(alpha) = b/sin(beta) => beta = arcsin(b*sin(alpha)/a)
		setBeta(Math.toDegrees(Math.asin(b * Math.sin(Math.toRadians(alpha)) / a)));
	    } else if (b != 0.0 && c != 0.0 && gamma != 0.0) {
		// b/sin(beta) = c/sin(gamma) => beta = arcsin(b*sin(gamma)/c)
		setBeta(Math.toDegrees(Math.asin(b * Math.sin(Math.toRadians(gamma)) / c)));
	    } else if (a != 0.0 && b != 0.0 && c != 0.0) {
		// Kosinussatz b²=a²+c²-2ac*cos(beta) => beta = arccos((a²+c²-b²)/2ac)
		setBeta(Math.toDegrees(Math.acos((a * a + c * c - b * b) / (2 * a * c))));
	    }
	    if (Double.isNaN(beta)) {
		beta = 0.0;
	    }
	}
	return beta;
    }

    public void setBeta(double beta) {
	this.beta = beta;
    }

    public double getC() {
	// Try to calculate c if it is 0.0
	if (c == 0.0) {
	    if (alpha != 0.0 && gamma != 0.0 && a != 0.0) {
		// c/sin(gamma) = a/sin(alpha) => c = sin(gamma)*a/sin(alpha)
		setC(Math.sin(gamma) * a / Math.sin(alpha));
	    } else if (beta != 0.0 && gamma != 0.0 && b != 0.0) {
		// c/sin(gamma) = b/sin(beta) => c = sin(gamma)*b/sin(beta)
		setC(Math.sin(gamma) * b / Math.sin(beta));
	    } else if (gamma != 0.0 && a != 0.0 && b != 0.0) {
		// Kosinussatz: c²=a²+b²-2a*b*cos(gamma)
		setC(Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(Math.toRadians(gamma))));
	    }
	    if (Double.isNaN(c)) {
		c = 0.0;
	    }
	}
	return c;
    }

    public void setC(double c) {
	this.c = c;
    }

    public double getGamma() {
	// Try to calculate gamma if it is 0.0
	if (gamma == 0.0) {
	    if (alpha != 0.0 && beta != 0.0) {
		// Die Winkelsumme muss 180 sein
		setGamma(180.0 - (alpha + beta));
	    } else if (a != 0.0 && c != 0.0 && alpha != 0.0) {
		// a/sin(alpha) = c/sin(gamma) => gamma = arcsin(c*sin(alpha)/a)
		setGamma(Math.toDegrees(Math.asin(c * Math.sin(Math.toRadians(alpha)) / a)));
	    } else if (b != 0.0 && c != 0.0 && beta != 0.0) {
		// b/sin(beta) = c/sin(gamma) => gamma = arcsin(c*sin(beta)/b)
		setGamma(Math.toDegrees(Math.asin(c * Math.sin(Math.toRadians(beta)) / b)));
	    } else if (a != 0.0 && b != 0.0 && c != 0.0) {
		// Kosinussatz: c²=a²+b²-2ab*cos(gamma) => gamma = arccos((a²+b²-c²)/2ab)
		setGamma(Math.toDegrees(Math.acos((a * a + b * b - c * c) / (2 * a * b))));
	    }
	    if (Double.isNaN(gamma)) {
		gamma = 0.0;
	    }
	}
	return gamma;
    }

    public void setGamma(double gamma) {
	this.gamma = gamma;
    }

    /**
     * @return the circumference size
     */
    @Override
    public double getCircumference() {
	// a perimeter needs all lengths to be > 0
	if (a == 0.0 || b == 0.0 || c == 0.0) {
	    return 0.0;
	}
	return a + b + c;
    }

    /**
     * @return the area size
     */
    @Override
    public double getArea() {
	// try to retrieve one height to calculate the area
	if (getHeightA() > 0.0 || getA() != 0.0) {
	    return getHeightA() * a * 0.5;
	} else if (getHeightB() > 0.0 || getB() != 0.0) {
	    return getHeightB() * b * 0.5;
	} else if (getHeightC() > 0.0 || getC() != 0.0) {
	    return getHeightC() * c * 0.5;
	}
	return 0.0;
    }
}
