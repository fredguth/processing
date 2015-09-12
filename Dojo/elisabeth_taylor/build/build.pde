import processing.pdf.*;
boolean record = false;

HPixelColorist colors;


void setup(){
	size(547,547);
	H.init(this).background(#603158);
	colors = new HPixelColorist("elizabeth.jpg");

	smooth();
	//frameRate(1);
	drawCircle(width/2,height/2,width);
}

void draw() {
	H.drawStage();
	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "elizabeth.pdf");
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


//(x,y)=position; d = diameter
void drawCircle(float x, float y, float d){

	HEllipse circle = new HEllipse();
	int currentColor=colors.getColor(x, y);


	circle
		.loc(x,y)
		.size(d)
		.anchorAt(H.CENTER)
		.strokeWeight(1)
		.stroke(currentColor)
		.fill(currentColor,75)//alpha 100
	;





	if (d>10){

		H.add(circle);
		drawCircle(x-d/4, y-d/4, d/2);
		drawCircle(x-d/4, y+d/4, d/2);
		drawCircle(x+d/4, y-d/4, d/2);
		drawCircle(x+d/4, y+d/4, d/2);

	}

}
