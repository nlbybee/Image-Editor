package com.mygdx.buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.imageeditor.Rec2D;

import Utility.IClickable;
import Utility.IHoverable;
import Utility.InputManager;

public class Button extends Rec2D implements IClickable, IHoverable{
	public enum ButtonState {Clicked, Hovered, None};
	public String ButtonText;
	private ButtonState _state;
	protected Color _startColor;
	private Color _hoveredColor;
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		_startColor = recColor;
		_hoveredColor = new Color(_startColor.r / 2f, _startColor.g / 2f, _startColor.b / 2f, 1);
		_state = ButtonState.None;
		InputManager.Instance.Clickables.add(this);
		InputManager.Instance.Hoverables.add(this);
	}
	
	public void onHovered() {
		if(_state == ButtonState.Clicked) return;
		if(_state == ButtonState.Hovered) return;
		_state = ButtonState.Hovered;
		_recColor = _hoveredColor;
		generateTexture();
	}
	
	public void onHoverExit() {
		_state = ButtonState.None;
		_recColor = _startColor;
		generateTexture();
	}
	
	public void onClickDown(Vector2 clickPosition) {
		if(_state == ButtonState.Clicked) return;
		_state = ButtonState.Clicked;
		_recColor = new Color(_startColor.r / 4f, _startColor.g / 4f, _startColor.b / 4f, 1);
		generateTexture();
	}
	
	public void onClickUp(Vector2 clickPosition) {
		if(_state == ButtonState.None) return;
		_state = ButtonState.Hovered;
		_recColor = _hoveredColor;
		generateTexture();
	}

	@Override
	public void onClickDragged(Vector2 clickPosition) {
		// TODO Auto-generated method stub
		
	}
}