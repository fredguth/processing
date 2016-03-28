HDrawablePool pool1, pool2, pool3;
HPixelColorist colors;


void setup(){
	size(800,800);
	H.init(this).background(#FFFFFF);
	smooth();
}

void draw(){
	pool1 = new HDrawablePool(200);
	pool2 = new HDrawablePool(200);
	pool3 = new HDrawablePool(6);
	pool3.autoAddToStage()
		.add(new HShape("tri.svg"))
		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.fill(#FF3300)
						.alpha(50)
						.strokeWeight(0)
						.loc(((int) random(800)), ((int) random(800)))
						.anchorAt(H.CENTER)
						.rotate((int)random(360));
					;


				}
			}
		)
	;
	pool1.autoAddToStage()
		.add(new HShape("bola.svg"))

		.layout(
			new HGridLayout()
			.startX(15)
			.startY(15)
			.spacing(60,60)
			.cols(16)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.size(15, 15)
						.strokeJoin(ROUND)
						.strokeCap(ROUND)
						.strokeWeight(0)
						.fill(#000000)
						.anchorAt(H.LEFT)
					;

				}
			}
		)
	;
	pool2.autoAddToStage()
		.add(new HShape("bola.svg"))

		.layout(
			new HGridLayout()
			.startX(45)
			.startY(45)
			.spacing(60,60)
			.cols(16)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.size(15, 15)
						.strokeJoin(ROUND)
						.strokeCap(ROUND)
						.strokeWeight(0)
						.fill(#000000)
						.anchorAt(H.LEFT)
					;


				}
			}
		)
	;
	pool1.requestAll();
	pool2.requestAll();

	H.drawStage();
	if (mousePressed) {
		pool3.drain(true);
 		pool3.shuffleRequestAll();
 		H.drawStage();

  }

}
