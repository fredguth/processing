import processing.pdf.*;
boolean record = false;


//int [] colors = {#D64453, #5BA5CB};
int [] colors = {#BF3434, #54458C};

void setup(){
	size(1024,668);
	H.init(this).background(#FEEADE);
	smooth();
//
	float u = (0.7*width)/10;//unit

	float diameter =4*u;
	int alpha=60;
	while (diameter <= 0.7*width){
		HEllipse left = new HEllipse();
		left
			.loc(diameter/2+width*0.15,height/2)
			.size(diameter)
			.anchorAt(H.CENTER)
			.noStroke()
			//.stroke(#CCCCCC)
			.fill(colors[1], alpha)//alpha 100
		;
		H.add(left);
		HEllipse right= new HEllipse();
		right
			.loc(0.85*width-diameter/2,height/2)
			.size(diameter)
			.anchorAt(H.CENTER)
			.noStroke()
			//.stroke(#CCCCCC)
			.fill(colors[0], alpha)//alpha 100
		;
		H.add(right);
		diameter+=u;
		alpha-=10;
	}

	H.drawStage();

}

void draw() {
	H.drawStage();
	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-slide1.pdf");
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
