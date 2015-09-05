import processing.pdf.*;
boolean record = false;

HPixelColorist colors;



void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();
	colors = new HPixelColorist("fredjulia.jpg");
	
}

void draw() {
	

	drawRect(width/2,height/2,width, height);

	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-recursion2.pdf");
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
void drawRect(float x, float y, float w, float h){
	
	HRect rectangle = new HRect();
	rectangle
		.loc(x,y)
		.size(w,h)
		.anchorAt(H.CENTER)
		.stroke(colors.getColor(x,y))
		.fill(colors.getColor(x,y),50)//alpha 100
	;

	H.add(rectangle);

	if (w>8 && h>8){

		drawRect(x-w/2, y, w/2, h/2);
		drawRect(x+w/2, y, w/2, h/2);
		drawRect(x, y-h/2, w/2, h/2);
		drawRect(x, y+h/2, w/2, h/2);

	}

}	

		


		