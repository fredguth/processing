HDrawablePool pool;
HPixelColorist colors;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();

	colors = new HPixelColorist("fredjulia.jpg");
	pool = new HDrawablePool(3600);
	pool.autoAddToStage()
		.add(new HRect())
		//.add(new HRect().rounding(10))
		.layout(
			new HGridLayout()
			.startX(5)
			.startY(5)
			.spacing(10,10)
			.cols(60)
		)
		.onCreate(
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.strokeWeight(1)
						.stroke(colors.getColor( d.x(), d.y()))
						.fill(colors.getColor( d.x(), d.y()))
						.size( 10 )
						//.rotate( (int)random(360) )
						//.loc( (int)random(width), (int)random(height) )
						.anchorAt(H.CENTER)
					;
				}
			}
		)
		.requestAll()
	;

	H.drawStage();
}


		