import processing.pdf.*;
boolean record = false;

HDrawablePool pool;
//HImage bach;
HPixelColorist colors;

int cellsize = 30;
void setup(){
	size(1028,1185);
	H.init(this).background(#202020);
	//bach = new HImage("bach.jpg");

	smooth();
	int cells = (int) width*height/cellsize;
	colors = new HPixelColorist("bach.jpg");

	pool = new HDrawablePool(cells);
	pool.autoAddToStage()
		//.add(new HEllipse())
		.add(new HRect().rounding(2))
		.layout(
			new HGridLayout()
			.startX(5)
			.startY(5)
			.spacing(cellsize,cellsize)
			.cols((int) width/cellsize)
		)
		.onCreate(
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.strokeWeight(1)
						.stroke(colors.getColor( d.x(), d.y()))
						.fill(colors.getColor( d.x(), d.y()),100-(int)random(25))
						.size( cellsize/4 + (int)random(4)*cellsize/4 )
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
		tmp = beginRecord(PDF, "bach.pdf");
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
