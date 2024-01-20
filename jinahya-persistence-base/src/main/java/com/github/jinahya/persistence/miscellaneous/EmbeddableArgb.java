package com.github.jinahya.persistence.miscellaneous;

import jakarta.persistence.*;
import jakarta.validation.Valid;
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

        @Override
        public String toString() {
            return super.toString() + '{' +
                    "alpha=" + alpha +
                    ",red=" + red +
                    ",green=" + green +
                    ",blue=" + blue +
                    '}';
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Populated populated)) return false;
            return alpha == populated.alpha
                    && red == populated.red
                    && green == populated.green
                    && blue == populated.blue;
        }

        @Override
        public int hashCode() {
            return Objects.hash(alpha, red, green, blue);
        }

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
        @Override
        public String toString() {
            return super.toString() + '{' +
                    "populated=" + populated +
                    '}';
        }

        @Override
        public boolean equals(final Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Unpopulated that)) return false;
            return Objects.equals(populated, that.populated);
        }

        @Override
        public int hashCode() {
            return Objects.hash(populated);
        }

        // ----------------------------------------------------------------------------------------- jakarta.persistence
        @PrePersist
        @PreUpdate
        private void unpopulate() {
            argb = populated.getArgb();
        }

        @PostLoad
        private void populate() {
            populated.setAlpha(argb);
        }

        // ------------------------------------------------------------------------------------------------------- alpha
        @Override
        public int getAlpha() {
//            return (getArgb() >> SHIFT_ALPHA) & MASK_COMPONENT;
            return populated.getAlpha();
        }

        @Override
        public void setAlpha(final int alpha) {
//            setArgb((getArgb() & ~MASK_ALPHA) | ((alpha & MASK_COMPONENT) << SHIFT_ALPHA));
            populated.setAlpha(alpha);
        }

        // --------------------------------------------------------------------------------------------------------- red
        @Override
        public int getRed() {
//            return (getArgb() >> SHIFT_RED) & MASK_COMPONENT;
            return populated.getRed();
        }

        @Override
        public void setRed(final int red) {
//            setArgb((getArgb() & ~MASK_RED) | ((red & MASK_COMPONENT) << SHIFT_RED));
            populated.setRed(red);
        }

        @Override
        public int getGreen() {
//            return (getArgb() & MASK_GREEN) >> SHIFT_GREEN;
            return populated.getGreen();
        }

        @Override
        public void setGreen(final int green) {
//            setArgb((getArgb() & ~MASK_GREEN) | ((green & MASK_COMPONENT) << SHIFT_GREEN));
            populated.setGreen(green);
        }

        @Override
        public int getBlue() {
//            return (getArgb() & MASK_BLUE) >> SHIFT_BLUE;
            return populated.getBlue();
        }

        @Override
        public void setBlue(final int blue) {
//            setArgb((getArgb() & ~MASK_BLUE) | (blue & MASK_COMPONENT) << SHIFT_BLUE);
            populated.setBlue(blue);
        }

        // --------------------------------------------------------------------------------------------------- populated

        // -------------------------------------------------------------------------------------------------------- argb
        @Override
        public int getArgb() {
            unpopulate();
            return argb;
        }

        @Override
        public void setArgb(final int argb) {
            this.argb = argb;
            populate();
        }

        // -------------------------------------------------------------------------------------------------------------
        @Valid
        @Transient
        @Setter(AccessLevel.NONE)
        @Getter(AccessLevel.NONE)
        private final Populated populated = new Populated();

        @Basic(optional = true)
        @Column(name = COLUMN_NAME_ARGB, nullable = true, insertable = true, updatable = true)
        @EqualsAndHashCode.Exclude
        @ToString.Exclude
        private int argb;
    }

    // ---------------------------------------------------------------------------------------------------- CONSTRUCTORS

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
