import processing.pdf.*;
boolean record = false;

int[] colors;



void setup(){
	size(800,450);
	H.init(this).background(#ECECEC);
	smooth();
	colors = {#D64453, #5BA5CB};
	float u = width/13;//unit
	HEllipse left = new HEllipse();
		
	H.drawStage();

}

void draw() {

	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-setp5.pdf");
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
	int currentColor = colors.getColor();
	circle
		.loc(x,y)
		.size(d)
		.anchorAt(H.CENTER)
		.stroke(currentColor)
		.fill(currentColor,50)//alpha 100
	;



	H.add(circle);

	if (d>20){

		drawCircle(x-d/2, y, d/2);
		drawCircle(x+d/2, y, d/2);
		drawCircle(x, y-d/2, d/2);
		drawCircle(x, y+d/2, d/2);

	}

}
