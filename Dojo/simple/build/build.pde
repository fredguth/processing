import processing.pdf.*;
boolean record = false;

HRect rect;

void setup() {
	size(512,768);
	H.init(this).background(#202020).autoClear(false);
	smooth();


	rect = new HRect(100).rounding(40);
	rect
		.strokeWeight(2)
		.stroke(#000000, 150)
		.fill(#ff3300,100)
		.loc(width/2,height/2)
		.anchorAt(H.BOTTOM)
		.rotation(45)
	;
	H.add(rect);

	HRotate rot = new HRotate()
		.target(rect)
		.speed(1)
	;

}

void draw() {

	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-mona.pdf");
	}

	if (tmp == null) {
		H.drawStage();
	} else {
		PGraphics g = tmp;
		boolean uses3D = false;
		float alpha = 1;
		H.stage().paintAll(g, uses3D, alpha);
	}

	if (record) {
		endRecord();
		record = false;
	}
	}

	void keyPressed() {
	if (key == 's') {
		record = true;
		draw();
	}
	}




		
