public class RainTypingGame {

	public static void main(String[] args) {
	
		int x = 0, y = 0, dx = 0, dy = 0, score = 0, levelProgress = 0, level = 1, lives = 3, z = 0;
		String text = "";
		boolean nextLevel = true;
		boolean flag = false;
		long startTime = System.currentTimeMillis();

		Zen.setFont("Helvetica-64");
		
		while (Zen.isRunning() && lives > 0) {
			
			if (text.length() == 0 || flag) {
				x = Zen.getZenWidth() /2; 
				if (level >= 3) {
					x = (int) (Math.random() * (Zen.getZenWidth() - 50)); //numbers start from a random x position
				}
				
				y = 0;
				dx = 0;
				
				if (level >= 5) {
					dx = (int) (Math.random() * 60) - 30;
				}
				
				dy = 3 * level;
				text = "" + (int) (Math.random() * 999);
				long elapsed = System.currentTimeMillis() - startTime;
				startTime = System.currentTimeMillis();
				
				if (z > 0 && nextLevel == true) score += 3000 / elapsed;
				if (z == 0) z++;
				
				if (nextLevel) {
					levelProgress++;
				}
				else {
					nextLevel = true;
				}
				
				//increments the level every 5 numbers
				if (levelProgress == 5) {
					level++;
					levelProgress = 0;
				}
				
				flag = false;
			}
		
			//change color depending on level
			if (level == 1) {              
				Zen.setColor(255, 0, 0);
			}
			else if (level == 2) {
				Zen.setColor(0, 200, 0);
			}
			else if (level == 3) {
				Zen.setColor(0, 0, 255);
			}
			else if (level == 4) {
				Zen.setColor(200, 200, 0);
			}
			else if (level == 5) {
				Zen.setColor(0, 255, 255);
			}
			else if (level == 6) {
				Zen.setColor(255, 0, 255);
			}
			else {
				Zen.setColor(255, 0, 127);
			}
			
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
			Zen.setColor(0, 255, 0);
			Zen.drawText(text, x, y);
			
			Zen.drawText("Level: " + level, 10,60);
			Zen.drawText("Score: " + score, 10,120);
			Zen.drawText("Lives: " + lives, 10,180);
			
			if (level >= 7) {
				Zen.drawText("" + (int) (Math.random() * 1000),(int) (Math.random() * Zen.getZenWidth()), (int) (Math.random()* Zen.getZenHeight()));
			}
			
			x += dx;
			y += dy;
			
			if (y >= Zen.getZenHeight()) {
				y = 0;
				text = "";
				nextLevel = false;
				lives--;
			}
			
			if (x > Zen.getZenWidth() -50) {
				x = 0;
			}
			else if (x < 0) {
				x = Zen.getZenWidth() -50;
			}
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();

			/* Reset the input to an empty string so the next iteration
			will only get the most recently pressed keys*/
			Zen.setEditText("");	
			
			if ( text != "") {
				for(int i=0; i < user.length(); i++) {
					char c = user.charAt(i);
					if(c == text.charAt(0)) text = text.substring(1,text.length()); // set text to everything except the first character
					else if (c == '=') {
						level += 2;
						nextLevel = false;
						flag = true;
					}
				}
			}
			
			Zen.flipBuffer();
			Zen.sleep(90);
		}
		
		Zen.setColor(0, 255, 0);
		Zen.drawText(text, x, y);
		Zen.drawText("GAME OVER", 30, Zen.getZenHeight()/2);
		Zen.drawText("Score: " + score, 10,120);
		Zen.flipBuffer();
	}
}