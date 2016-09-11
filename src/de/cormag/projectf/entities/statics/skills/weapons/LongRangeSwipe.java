package de.cormag.projectf.entities.statics.skills.weapons;

import java.awt.Graphics;
import java.awt.Rectangle;

import de.cormag.projectf.entities.creatures.humans.controlable.Player;
import de.cormag.projectf.entities.statics.skills.Skills;
import de.cormag.projectf.gfx.Assets;
import de.cormag.projectf.main.Handler;
import de.cormag.projectf.tiles.Tile;
import de.cormag.projectf.utils.time.GameTime;

public class LongRangeSwipe extends Skills {

	private static final long serialVersionUID = 1L;
	
	private int topMovement;
	private Rectangle borderAdjustment;
	private String direction;
	private boolean setOnce;
	
	public static final int WIDTH = 128, HEIGHT = 32;
	public static final int DMG = 20;

	public LongRangeSwipe(Handler handler, float x, float y) {
		super(handler, x, y, WIDTH, HEIGHT, DMG);
		
		direction = "";
		setOnce = false;
		
		topMovement = 0;

	}

	@Override
	public void update(final GameTime gameTime) {
		super.update(gameTime);
		
		if(borderAdjustment != null && (borderAdjustment.y + yOffset <= 0 || borderAdjustment.y + yOffset >= handler.getWorld().getHeight() * Tile.TILEHEIGHT
				|| borderAdjustment.x + xOffset <= 0 || borderAdjustment.x + xOffset >= handler.getWorld().getWidth() * Tile.TILEWIDTH)){
			
			direction = "";
			setOnce = false;
			
			topMovement = 0;
			
			handler.getWorld().getEntityManager().removeEntity(this);
			
		}
		
		setDirection();
		
		adjustHitbox();
		
		topMovement += 7;
	
	}

	@Override
	public void render(Graphics g, final GameTime gameTime) {
		renderWeaponSkill(g);
	}
	
	public void setDirection(){
		
		if(!setOnce){
		
			if(handler.getPlayer().getSteadyAnimation().equals(Assets.player_up[1])){
				
				direction = "up";
				
			}else if(handler.getPlayer().getSteadyAnimation().equals(Assets.player_down[1])){
				
				direction = "down";
				
			}else if(handler.getPlayer().getSteadyAnimation().equals(Assets.player_left[1])){
				
				direction = "left";
				
			}else if(handler.getPlayer().getSteadyAnimation().equals(Assets.player_right[1])){
				
				direction = "right";
				
			}
		}
		
		setOnce = true;
		
	}
	
	public void renderWeaponSkill(Graphics g){

		
		if(direction.equals("up")){
			borderAdjustment = new Rectangle((int) (getX() - WIDTH / 4), (int) (getY() - handler.getPlayer().getCurrentWeapon().getBounds().height) - topMovement, WIDTH, HEIGHT);
			g.drawImage(Assets.longRangeSwipeTop, borderAdjustment.x, borderAdjustment.y, borderAdjustment.width, borderAdjustment.height, null);


		}else if(direction.equals("down")){
			borderAdjustment = new Rectangle((int) (getX() - WIDTH / 4), (int) (getY() + Player.DEFAULT_CREATURE_HEIGHT + handler.getPlayer().getCurrentWeapon().getBounds().height) + topMovement, WIDTH, HEIGHT);
			g.drawImage(Assets.longRangeSwipeBottom, borderAdjustment.x, borderAdjustment.y, borderAdjustment.width, borderAdjustment.height, null);


		}else if(direction.equals("left")){
			borderAdjustment = new Rectangle((int) (getX() - handler.getPlayer().getCurrentWeapon().getBounds().width) - topMovement, (int) (getY()), HEIGHT, WIDTH);
			g.drawImage(Assets.longRangeSwipeLeft, borderAdjustment.x, borderAdjustment.y, borderAdjustment.width, borderAdjustment.height, null);


		}else if(direction.equals("right")){
			borderAdjustment = new Rectangle((int) (getX() + Player.DEFAULT_CREATURE_WIDTH + handler.getPlayer().getCurrentWeapon().getBounds().width) + topMovement, (int) (getY()) , HEIGHT, WIDTH);
			g.drawImage(Assets.longRangeSwipeRight, borderAdjustment.x, borderAdjustment.y, borderAdjustment.width, borderAdjustment.height, null);


		}
		
	}
	
	public void adjustHitbox(){
		
		if(direction.equals("up")){
			getBounds().x = -WIDTH / 4;
			getBounds().y = -handler.getPlayer().getCurrentWeapon().getBounds().height - topMovement;
			getBounds().width = WIDTH;
			getBounds().height = HEIGHT;
			
		}else if(direction.equals("down")){
			getBounds().x = -WIDTH / 4;
			getBounds().y = Player.DEFAULT_CREATURE_HEIGHT + handler.getPlayer().getCurrentWeapon().getBounds().height + topMovement;
			getBounds().width = WIDTH;
			getBounds().height = HEIGHT;			
		}
		else if(direction.equals("left")){
			getBounds().x = -handler.getPlayer().getCurrentWeapon().getBounds().width - topMovement;
			getBounds().y = 0;
			getBounds().width = HEIGHT;
			getBounds().height = WIDTH;
			
		}
		else if(direction.equals("right")){
			getBounds().x = Player.DEFAULT_CREATURE_WIDTH + handler.getPlayer().getCurrentWeapon().getBounds().width + topMovement;
			getBounds().y = 0;
			getBounds().width = HEIGHT;
			getBounds().height = WIDTH;
			
		}
		
	}

}
