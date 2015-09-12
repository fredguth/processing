import processing.pdf.*;
boolean record = false;
HEllipse d1,d2;
//HDrawablePool pool;
int [] colors={#00FFFF, #00FF00};

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();
	float r=80;
	int offset=50;
	while (r<height) {

		d1 = new HEllipse();
		d1
			.strokeWeight(1)
			.stroke(#FF3300)
			.fill(colors[0],10)
			.size( 2*r, 2*r )
			.loc( width/2, r-offset)
			.anchorAt(H.CENTER)
		;
		d2 = new HEllipse();
		d2
			.strokeWeight(1)
			.stroke(#FF3300)
			.fill(colors[1],10)
			.size( 2*r, 2*r )
			.loc( width/2, height+offset-r )
			.anchorAt(H.CENTER)
		;
		r=r*1.3;
		H.add(d1);
	  H.add(d2);
	}

	H.drawStage();
}
