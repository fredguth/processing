import processing.pdf.*;
boolean record = false;
HEllipse circle = new HEllipse();

void setup(){
	size(768,768);
	H.init(this).background(#603158);
	smooth();
	//frameRate(1);

}



//(x,y)=position; d = diameter
void drawCircle(float x, float y, float d, boolean isBlue){


	int red = #fbfbcc;
	int blue = #99cccc;
	int currentColor;

	if (isBlue){
		currentColor=blue;
	}
	else {
		currentColor=red;
	}


	circle
		.loc(x,y)
		.size(d)
		.anchorAt(H.CENTER)
		.strokeWeight(2)
		.stroke(#333333)
		.fill(currentColor,80)//alpha 100
	;





	if (d>15){

		H.add(circle);
		drawCircle(x-d/2, y, d/2, !isBlue);
		drawCircle(x+d/2, y, d/2, !isBlue);
		drawCircle(x, y-d/2, d/2, !isBlue);
		drawCircle(x, y+d/2, d/2, !isBlue);

	}

}

void draw() {
	drawCircle(width/2,height/2,width/2, true);
	// H.drawStage();
	// PGraphics tmp = null;
	//
	// if (record) {
	// 	tmp = beginRecord(PDF, "render-recursao.pdf");
	// }
	//
	// if (tmp == null) {
	// 	H.drawStage();
	// 	} else {
	// 		PGraphics g = tmp;
	// 		boolean uses3D = false;
	// 		float alpha = 1;
	// 		H.stage().paintAll(g, uses3D, alpha);
	// 	}
	//
	// 	if (record) {
	// 		endRecord();
	// 		record = false;
	// 	}
	// }
	//
	// void keyPressed() {
	// 	if (key == 's') {
	// 		record = true;
	// 		draw();
	// 	}
	}
