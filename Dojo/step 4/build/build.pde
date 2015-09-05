import processing.pdf.*;
boolean record = false;

HDrawablePool pool;
HPixelColorist colors;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();

	colors = new HPixelColorist("fredjulia.jpg");
	pool = new HDrawablePool(3600);
	pool.autoAddToStage()
		.add(new HEllipse())
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
						.size( 5+(int)random(5) )
						//.rotate( (int)random(360) )
						//.loc( (int)random(width), (int)random(height) )
						.anchorAt(H.CENTER)
					;
				}
			}
		)
		.requestAll()
	;

	
}

void draw() {
	H.drawStage();
	PGraphics tmp = null;

	if (record) {
		tmp = beginRecord(PDF, "render-fredjulia.pdf");
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

		


		