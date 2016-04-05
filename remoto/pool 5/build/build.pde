import processing.pdf.*;
import hype.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;

HDrawablePool pool1, pool2, pool3;
HPixelColorist colors1;
HColorPool colors2, colors3, colors4;
HCanvas c1,c2,c3;
boolean record = false;


void setup(){
	size(900,300);
	H.init(this).background(#ffffff);

	smooth();
	colors1 = new HPixelColorist("mancha1080.png").fillOnly();
	colors2 = new HColorPool( #66ccff, #ECECEC, #0066ff, #003366, #ff761a, #0066ff, #003366, #ff761a);
	colors3 = new HColorPool( #66ccff, #ECECEC, #0066ff, #003366, #ff761a);
	colors4 = new HColorPool( #66ccff, #ECECEC, #66ccff, #ECECEC,#0066ff, #003366, #ff761a);
}

void draw(){
	pool1 = new HDrawablePool(120);
	//pool2 = new HDrawablePool(120);
	pool1.autoAddToStage()
		// .add(new HShape("200-tilman-14.svg"))
		// .add(new HShape("200-tilman-22.svg"))
		// .add(new HShape("200-tilman-27.svg"))
		.add(new HShape("225-tilman-18.svg"))
		.add(new HShape("250-tilman-10.svg"))
		.add(new HShape("250-tilman-13.svg"))
		.add(new HShape("250-tilman-17.svg"))
		.add(new HShape("250-tilman-21.svg"))
		.add(new HShape("275-tilman-11.svg"))
		.add(new HShape("275-tilman-19.svg"))
		.add(new HShape("275-tilman-23.svg"))
		.add(new HShape("275-tilman-24.svg"))
		.add(new HShape("275-tilman-28.svg"))
		.add(new HShape("300-tilman-07.svg"))
		.add(new HShape("300-tilman-31.svg"))


		.layout(
			new HGridLayout()
			.startX(0)
			.startY(0)
			.spacing(100,100)
			.cols(18)
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
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y())<#666666){
						d.randomColors(colors2.fillOnly());
					}else {
						d.alpha(0);
					}

				}

					//d.randomColors(colors1.fillOnly());
			}
		);

	pool2 = new HDrawablePool(120);
	//pool2 = new HDrawablePool(120);
	pool2.autoAddToStage()
		.add(new HShape("tilman-01.svg"))
		.add(new HShape("tilman-02.svg"))
		.add(new HShape("tilman-04.svg"))
		.add(new HShape("tilman-05.svg"))
		.add(new HShape("tilman-06.svg"))
		.add(new HShape("tilman-07.svg"))
		.add(new HShape("tilman-08.svg"))
		.add(new HShape("tilman-09.svg"))
		.add(new HShape("tilman-10.svg"))
		.add(new HShape("tilman-11.svg"))
		.add(new HShape("tilman-12.svg"))
		.add(new HShape("tilman-13.svg"))
		.add(new HShape("tilman-14.svg"))
		.add(new HShape("tilman-15.svg"))
		.add(new HShape("tilman-16.svg"))
		.add(new HShape("tilman-17.svg"))
		.add(new HShape("tilman-18.svg"))
		.add(new HShape("tilman-19.svg"))



		.layout(
			new HGridLayout()
			.startX(0)
			.startY(0)
			.spacing(100,100)
			.cols(18)
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
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y())>#CCCCCC){
						d.alpha(0);
						//d.randomColors(colors2.fillOnly());
					}else {
						if (colors1.getColor(d.x(), d.y())>#666666){
						d.fill(#00FF00);
						d.randomColors(colors3.fillOnly());
						}
						else{
						d.alpha(0);
						}

					}

				}

					//d.randomColors(colors1.fillOnly());
			}
		)
	;

	pool3 = new HDrawablePool(120);
	//pool3 = new HDrawablePool(120);
	pool3.autoAddToStage()
		.add(new HShape("025-tilman-06.svg"))
		.add(new HShape("050-tilman-08.svg"))
		.add(new HShape("050-tilman-25.svg"))
		.add(new HShape("050-tilman-26.svg"))
		.add(new HShape("075-tilman-01.svg"))
		.add(new HShape("075-tilman-02.svg"))
		.add(new HShape("075-tilman-15.svg"))
		.add(new HShape("075-tilman-30.svg"))


		.layout(
			new HGridLayout()
			.startX(0)
			.startY(0)
			.spacing(100,100)
			.cols(18)
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
						// .fill(colors1.getColor(d.x(), d.y()))
					;
					d.rotate( (int)random(4) * 90 );
					if (colors1.getColor(d.x(), d.y())>#CCCCCC){

						d.randomColors(colors4.fillOnly());
					}else {
						d.alpha(0);

					}

				}

					//d.randomColors(colors1.fillOnly());
			}
		)
	;
	// pool3.autoAddToStage()
	// 	.add(new HShape("tilman-01.svg"))
	// 	.add(new HShape("tilman-02.svg"))
	// 	.add(new HShape("tilman-04.svg"))
	// 	.add(new HShape("tilman-05.svg"))
	// 	.add(new HShape("tilman-06.svg"))
	// 	.add(new HShape("tilman-07.svg"))
	// 	.add(new HShape("tilman-08.svg"))
	// 	.add(new HShape("tilman-09.svg"))
	// 	.add(new HShape("tilman-10.svg"))
	// 	.add(new HShape("tilman-11.svg"))
	// 	.add(new HShape("tilman-12.svg"))
	// 	.add(new HShape("tilman-13.svg"))
	// 	.add(new HShape("tilman-14.svg"))
	// 	.add(new HShape("tilman-15.svg"))
	// 	.add(new HShape("tilman-16.svg"))
	// 	.add(new HShape("tilman-17.svg"))
	// 	.add(new HShape("tilman-18.svg"))
	// 	.add(new HShape("tilman-19.svg"))
	// 	.layout(
	// 		new HGridLayout()
	// 		.startX(0)
	// 		.startY(50)
	// 		.spacing(100,100)
	// 		.cols(18)
	// 	)

	// 	.onRequest(
	// 		new HCallback() {
	// 			public void run(Object obj) {
	// 				HShape d = (HShape) obj;
	// 				d
	// 					.enableStyle(false)
	// 					.noStroke()
	// 					.anchorAt(H.CENTER)
	// 					.size(100,100)
	// 					// .fill(colors1.getColor(d.x(), d.y()))
	// 				;
	// 				d.rotate( (int)random(4) * 90 );
	// 				if (colors1.getColor(d.x(), d.y()) >= (#666666) &&
	// 						colors1.getColor(d.x(), d.y()) <= (#CCCCCC)	) {
	// 					// log(colors1.getColor(d.x(), d.y()));
	// 					d.randomColors(colors2.fillOnly());
	// 				}
	// 				else {
	// 					d.alpha(0);
	// 				}

	// 				//d.randomColors(colors1.fillOnly());
	// 			}
	// 		}
	// 	)
	// ;



	if (mousePressed) {
			PGraphics tmp = beginRecord(PDF, "frame-####.pdf");
			pool1.shuffleRequestAll();
	 		pool2.shuffleRequestAll();
	 		pool3.shuffleRequestAll();
	 		H.stage().paintAll(tmp, false, 1);
			endRecord();
	 		H.drawStage();
			pool1.drain(true);
			pool2.drain(true);
			pool3.drain(true);





  	}

}

