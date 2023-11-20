package com.foxminded.romangrygorczuk.charcount;

public abstract class CharCounterDecorator implements CharCounter {

    protected CharCounter decorated;

    public CharCounterDecorator(CharCounter decorated) {
        this.decorated = decorated;
    }
}
