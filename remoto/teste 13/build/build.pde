import processing.pdf.*;
import hype.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;

HDrawablePool pool1;

HColorPool colors;


void setup(){
	size(500,500);
	H.init(this).background(#FFFFFF);
	smooth();
	colors = new HColorPool(#FFFFFF, #F9BF00, #3DA6AB, #96959C, #D7BC96, #653B96, #F6A39E, #2BBCBF, #EF4A23);


}

void draw(){
	pool1 = new HDrawablePool(50);
	pool1.autoAddToStage()
		.add(new HShape("muda1.svg"),16)
		.add(new HShape("muda2.svg"),16)
		.add(new HShape("muda3.svg"),16)
		.add(new HShape("muda4.svg"),16)
		.add(new HShape("muda5.svg"),16)
		.add(new HShape("muda6.svg"),16)
		.add(new HShape("muda7.svg"),1)
		.add(new HShape("muda8.svg"),1)
		.add(new HShape("muda9.svg"),1)

		.layout(
			new HGridLayout()
			.startX(50)
			.startY(50)
			.spacing(100,100)
			.cols(5)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.anchorAt(H.CENTER)
						.size(100,100)
					;
					d.rotate( (int)random(4) * 90 );
					d.randomColors(colors.fillOnly());

				}
			}
		)
	;



	if (mousePressed) {
			PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
			pool1.shuffleRequestAll();
	 		H.stage().paintAll(tmp, false, 1);
			endRecord();
	 		H.drawStage();
			pool1.drain(true);

  	}

}

