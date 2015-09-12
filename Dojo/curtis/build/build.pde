import processing.pdf.*;
boolean record = false;

HDrawablePool pool;
int [] colors = {#FFFFFF, #0000FF};
int position =0;

void setup(){
	size(600,600);
	H.init(this).background(#ececec);
	smooth();

	//colors = new HColorPool(#FF3300, #cccccc);
	pool = new HDrawablePool(900);
	pool.autoAddToStage()
		.add(new HShape("curtis.svg"))
		//.add(new HShape("curtis3.svg"))
		//.add(new HRect().rounding(10))
		.layout(
			new HGridLayout()
			.startX(25)
			.startY(25)
			.spacing(50,50)
			.cols(12)
		)
		.onCreate(
			new HCallback() {
				public void run(Object obj) {
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.stroke(#111111)
						.strokeWeight(1)
						.strokeJoin(ROUND)
						.strokeCap(ROUND)
						.size( 50 )
						.rotate( (position%4)*90 )
						//.loc( (int)random(width), (int)random(height) )
						.anchorAt(H.CENTER)
					;
					//d.randomColors( colors.fillOnly() );
					position++;
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
		tmp = beginRecord(PDF, "render-curtis.pdf");
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

		


		