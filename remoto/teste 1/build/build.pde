HDrawablePool pool;
HColorPool colors;

void setup(){
	size(500,500);
	H.init(this).background(#FFFFFF);
	colors = new HColorPool(#ECECEC,  #D7191B, #1B2B3C, #190A0B);

	smooth();



}
void draw(){
	pool = new HDrawablePool(100);
	pool.autoAddToStage()
		.add(new HShape("quarto.svg"))
		// .add(new HShape("svg2.svg"))
		// .add(new HShape("svg3.svg"))
		// .add(new HShape("svg4.svg"))
		// .add(new HShape("svg5.svg"))
		// .add(new HShape("svg6.svg"))

		.layout(
			new HGridLayout()
			.startX(25)
			.startY(25)
			.spacing(50,50)
			.cols(10)
		)

		.onRequest(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.strokeJoin(ROUND)
						.strokeCap(ROUND)
						.strokeWeight(0)
						.anchorAt(H.CENTER)
					;
					d.rotate( (int)random(4) * 90 );
					d.randomColors(colors.fillOnly());
				}
			}
		)
	;


	if (mousePressed) {
 		pool.shuffleRequestAll();
 		H.drawStage();
    pool.drain(true);
  }
}

