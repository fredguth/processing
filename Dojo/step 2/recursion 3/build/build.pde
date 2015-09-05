import processing.pdf.*;
boolean record = false;

HColorPool colors;



void setup(){
	size(600,600);
	H.init(this).background(#ECECEC);
	smooth();
	colors = new HColorPool(#D64453, #5BA5CB);
	
}

void draw() {
	

	drawCircle(width/2,height/2,width/2);

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

		


		