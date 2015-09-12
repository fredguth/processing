HPath d1, d2, d3, d4, d5, d6;
float u=100;


void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();

//hexatriangle

		d1 = new HPath();
		d1
			.triangle( H.EQUILATERAL, H.TOP )
			.noStroke()
			.fill(#FF0000,100)
			.size(sqrt(3)*u/2 )
			.loc( 0, 0 )
			.anchorAt(H.TOP|H.LEFT)
		;
		H.add(d1);

		d2 = new HPath();
		d2
			.triangle( H.EQUILATERAL, H.BOTTOM )
			.noStroke()
			.fill(#00FF00,100)
			.size( sqrt(3)*u/2 )//size is triangle height, not its side
			.loc( u/2,0 )
			.anchorAt(H.TOP|H.LEFT)
		;
		H.add(d2);

		d3 = new HPath();
		d3
			.triangle( H.EQUILATERAL, H.TOP )
			.noStroke()
			.fill(#FF0000,100)
			.size(sqrt(3)*u/2 )
			.loc( u, 0 )
			.anchorAt(H.TOP|H.LEFT)
		;
		H.add(d3);

		d4 = new HPath();
		d4
			.triangle( H.EQUILATERAL, H.BOTTOM )
			.noStroke()
			.fill(#00FF00,100)
			.size( sqrt(3)*u/2 )//size is triangle height, not its side
			.loc( 0,sqrt(3)*u/2 )
			.anchorAt(H.TOP|H.LEFT)
		;
		H.add(d4);

		d5 = new HPath();
		d5
			.triangle( H.EQUILATERAL, H.TOP )
			.noStroke()
			.fill(#FF0000,100)
			.size(sqrt(3)*u/2 )
			.loc( u/2, sqrt(3)*u/2 )
			.anchorAt(H.TOP|H.LEFT)
		;
		H.add(d5);

		d6 = new HPath();
		d6
			.triangle( H.EQUILATERAL, H.BOTTOM )
			.noStroke()
			.fill(#00FF00,100)
			.size( sqrt(3)*u/2 )//size is triangle height, not its side
			.loc( u,sqrt(3)*u/2 )
			.anchorAt(H.TOP|H.LEFT)
		;
		H.add(d6);

	H.drawStage();
}
void draw() {


	line(u/2, 0, u/2, height);
}
// import processing.pdf.*;
// boolean record = false;
//
// HDrawablePool pool;
// HPixelColorist colors;
//
// void setup(){
// 	size(1200,1200);
// 	H.init(this).background(#202020);
// 	smooth();
// 	int cells = (int) width*height/50;
// 	colors = new HPixelColorist("abaporu.jpg");
//
// 	pool = new HDrawablePool(cells);
// 	pool.autoAddToStage()
// 		.add(new HEllipse())
// 		//.add(new HRect().rounding(10))
// 		.layout(
// 			new HGridLayout()
// 			.startX(5)
// 			.startY(5)
// 			.spacing(10,10)
// 			.cols((int) width/10)
// 		)
// 		.onCreate(
// 			new HCallback() {
// 				public void run(Object obj) {
// 					HDrawable d = (HDrawable) obj;
// 					d
// 						.strokeWeight(1)
// 						.stroke(colors.getColor( d.x(), d.y()))
// 						.fill(colors.getColor( d.x(), d.y()))
// 						.size( 5+(int)random(6) )
// 						//.rotate( (int)random(360) )
// 						//.loc( (int)random(width), (int)random(height) )
// 						.anchorAt(H.CENTER)
// 					;
// 				}
// 			}
// 		)
// 		.requestAll()
// 	;
//
//
// }
//
// void draw() {
// 	H.drawStage();
// 	PGraphics tmp = null;
//
// 	if (record) {
// 		tmp = beginRecord(PDF, "mouro.pdf");
// 	}
//
// 	if (tmp == null) {
// 		H.drawStage();
// 	} else {
// 		PGraphics g = tmp;
// 		boolean uses3D = false;
// 		float alpha = 1;
// 		H.stage().paintAll(g, uses3D, alpha);
// 	}
//
// 	if (record) {
// 		endRecord();
// 		record = false;
// 	}
// }
//
// void keyPressed() {
// 	if (key == 's') {
// 		record = true;
// 		draw();
// 	}
// }
