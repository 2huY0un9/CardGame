package com.zy.card.util;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyImageView extends ImageView {
    private boolean Clickable = false;
    private int mark;

    private int floor;

    public MyImageView(){
        setOnMouseEntered(mouseEvent -> {
            setEffect(new DropShadow());
        });
        setOnMouseExited(mouseEvent -> {
            setEffect(null);
        });
        setOpacity(0.5);
        setMouseTransparent(true);
        Clickable = false;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    public int getMark() {
        return mark;
    }

    public void setClickable(boolean clickable) {
        Clickable = clickable;
    }

    public boolean isClickable() {
        return Clickable;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void Disable(){
        setMouseTransparent(true);
    }

    public void able(){
        setMouseTransparent(false);
    }
}
