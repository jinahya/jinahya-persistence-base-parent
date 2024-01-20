package com.github.jinahya.persistence.miscellaneous;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.io.Serial;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class EmbeddableArgb extends EmbeddableColor {

    @Serial
    private static final long serialVersionUID = -3018457863895745411L;

    public static final int MIN_COMPONENT = 0;

    public static final int MAX_COMPONENT = 255;

    @Getter
    @NoArgsConstructor
    public static class Populated extends EmbeddableArgb {

        @Serial
        private static final long serialVersionUID = 1051579977611218663L;

        public static final String COLUMN_NAME_ALPHA = "alpha";

        public static final String COLUMN_NAME_RED = "red";

        public static final String COLUMN_NAME_GREEN = "green";

        public static final String COLUMN_NAME_BLUE = "blue";

        // ------------------------------------------------------------------------------------------------------- alpha
        @Override
        public void setAlpha(final int alpha) {
            this.alpha = alpha & MAX_COMPONENT;
        }

        // --------------------------------------------------------------------------------------------------------- red
        @Override
        public void setRed(final int red) {
            this.red = red & MAX_COMPONENT;
        }

        // ------------------------------------------------------------------------------------------------------- green
        @Override
        public void setGreen(final int green) {
            this.green = green & MAX_COMPONENT;
        }

        // -------------------------------------------------------------------------------------------------------- blue
        @Override
        public void setBlue(final int blue) {
            this.blue = blue & MAX_COMPONENT;
        }

        // -------------------------------------------------------------------------------------------------------------
        @Max(MAX_COMPONENT)
        @Min(MIN_COMPONENT)
        @Basic(optional = false)
        @Column(name = COLUMN_NAME_ALPHA, nullable = false, insertable = true, updatable = true)
        private int alpha;

        @Max(MAX_COMPONENT)
        @Min(MIN_COMPONENT)
        @Basic(optional = false)
        @Column(name = COLUMN_NAME_RED, nullable = false, insertable = true, updatable = true)
        private int red;

        @Max(MAX_COMPONENT)
        @Min(MIN_COMPONENT)
        @Basic(optional = false)
        @Column(name = COLUMN_NAME_GREEN, nullable = false, insertable = true, updatable = true)
        private int green;

        @Max(MAX_COMPONENT)
        @Min(MIN_COMPONENT)
        @Basic(optional = false)
        @Column(name = COLUMN_NAME_BLUE, nullable = false, insertable = true, updatable = true)
        private int blue;
    }

    @Setter
    @Getter
    @NoArgsConstructor
    public static class Unpopulated extends EmbeddableArgb {

        @Serial
        private static final long serialVersionUID = 4780553273089902066L;

        public static final String COLUMN_NAME_ARGB = "argb";

        // ------------------------------------------------------------------------------------------------ CONSTRUCTORS

        // -------------------------------------------------------------------------------------------- java.lang.Object

        // ----------------------------------------------------------------------------------------- jakarta.persistence
//        @PrePersist
//        @PreUpdate
//        private void unpopulate() {
//            argb = populated.getArgb();
//        }
//
//        @PostLoad
//        private void populate() {
//            populated.setAlpha(argb);
//        }

        // ------------------------------------------------------------------------------------------------------- alpha
        @Override
        public int getAlpha() {
            return getArgb() >>> 24;
        }

        @Override
        public void setAlpha(final int alpha) {
            setArgb((getArgb() & 0x00FFFFFF) | ((alpha & 0xFF) << 24));
        }

        // --------------------------------------------------------------------------------------------------------- red
        @Override
        public int getRed() {
            return (getArgb() >> 16) & 0xFF;
        }

        @Override
        public void setRed(final int red) {
            setArgb((getArgb() & 0xFF00FFFF) | ((red & 0xFF) << 16));
        }

        // ------------------------------------------------------------------------------------------------------- green
        @Override
        public int getGreen() {
            return (getArgb() >> 8) & 0xFF;
        }

        @Override
        public void setGreen(final int green) {
            setArgb((getArgb() & 0xFFFF00FF) | ((green & 0xFF) << 8));
        }

        // -------------------------------------------------------------------------------------------------------- blue
        @Override
        public int getBlue() {
            return getArgb() & 0xFF;
        }

        @Override
        public void setBlue(final int blue) {
            setArgb((getArgb() & 0xFFFFFF00) | (blue & 0xFF));
        }

        // --------------------------------------------------------------------------------------------------- populated

        // -------------------------------------------------------------------------------------------------------- argb
        @Override
        public int getArgb() {
            return argb;
        }

        @Override
        public void setArgb(final int argb) {
            this.argb = argb;
        }

        // -------------------------------------------------------------------------------------------------------------
        @Basic(optional = true)
        @Column(name = COLUMN_NAME_ARGB, nullable = true, insertable = true, updatable = true)
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        private int argb;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

    // ------------------------------------------------------------------------------------------------ java.lang.Object
    @Override
    public final String toString() {
        return super.toString() + '{' +
                "alpha=" + getAlpha() +
                "red=" + getRed() +
                "green=" + getGreen() +
                "blue=" + getBlue() +
                '}';
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof EmbeddableArgb that)) return false;
        return getAlpha() == that.getAlpha() &&
                getRed() == that.getRed() &&
                getGreen() == that.getGreen() &&
                getBlue() == that.getBlue();
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getAlpha(), getRed(), getGreen(), getBlue());
    }

    // ------------------------------------------------------------------------------------------------------------ argb
    public int getArgb() {
        return (getAlpha() << 24)
                | (getRed() << 16)
                | (getGreen() << 8)
                | getBlue();
    }

    public void setArgb(final int argb) {
        setAlpha((argb >>> 24));
        setRed((argb >> 16) & 0xFF);
        setGreen((argb >> 8) & 0xFF);
        setBlue(argb & 0xFF);
    }

    // ----------------------------------------------------------------------------------------------------------- alpha

    /**
     * Returns current value of {@code alpha} component.
     *
     * @return current value of the {@code alpha} component.
     */
    public abstract int getAlpha();

    /**
     * Replaces current value of {@code alpha} component with lower {@value Byte#SIZE} bit of specified value.
     *
     * @param alpha new value for the {@code alpha} component.
     */
    public abstract void setAlpha(int alpha);

    // ------------------------------------------------------------------------------------------------------------- red
    public abstract int getRed();

    public abstract void setRed(int red);

    // ----------------------------------------------------------------------------------------------------------- green
    public abstract int getGreen();

    public abstract void setGreen(int green);

    // ------------------------------------------------------------------------------------------------------------ blue
    public abstract int getBlue();

    public abstract void setBlue(int blue);
}
