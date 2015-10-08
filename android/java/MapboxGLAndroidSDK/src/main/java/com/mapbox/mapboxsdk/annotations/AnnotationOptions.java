package com.mapbox.mapboxsdk.annotations;

public abstract class AnnotationOptions {

    protected Annotation annotation;

    protected AnnotationOptions() {
    }

    public AnnotationOptions alpha(float alpha) {
        annotation.setAlpha(alpha);
        return this;
    }

    public float getAlpha() {
        return annotation.getAlpha();
    }

    public boolean isVisible() {
        return annotation.isVisible();
    }

}
