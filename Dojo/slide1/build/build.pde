import hype.*;
import processing.pdf.*;
boolean record = false;


//int [] colors = {#D64453, #5BA5CB};
String [] interesses = {"Azulejos", "Processing"};

void setup(){
	size(1024,568);
	H.init(this).background(#D9CCC1);
	smooth();
//
	float u = (0.7*width)/10;//unit

	float diameter =4*u;
	int alpha=60;
	for (int i=0;i<interesses.lenght;i++){
		HText interesse = new HText(interesses[i]);
		interesse
			.loc((int) random (width), (int) random (height))
			// .size(diameter)
			// .anchorAt(H.CENTER)
			// .noStroke()
			// //.stroke(#CCCCCC)
			// .fill(colors[1], alpha)//alpha 100
		;
		H.add(interesse);

	}

	H.drawStage();

}

void draw() {
	H.drawStage();
	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-slide2.pdf");
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
