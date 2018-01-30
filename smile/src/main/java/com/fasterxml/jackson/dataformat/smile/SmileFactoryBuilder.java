package com.fasterxml.jackson.dataformat.smile;

import com.fasterxml.jackson.core.base.DecorableTSFactory.DecorableTSFBuilder;

/**
 * {@link com.fasterxml.jackson.core.TokenStreamFactory.TSFBuilder}
 * implementation for constructing {@link SmileFactory}
 * instances.
 *
 * @since 3.0
 */
public class SmileFactoryBuilder extends DecorableTSFBuilder<SmileFactory, SmileFactoryBuilder>
{
    /*
    /**********************************************************
    /* Configuration
    /**********************************************************
     */

    /**
     * Set of {@link SmileParser.Feature}s enabled, as bitmask.
     */
    protected int _formatParserFeatures;

    /**
     * Set of {@link SmileGenerator.Feature}s enabled, as bitmask.
     */
    protected int _formatGeneratorFeatures;

    /*
    /**********************************************************
    /* Life cycle
    /**********************************************************
     */

    protected SmileFactoryBuilder() {
        _formatParserFeatures = SmileFactory.DEFAULT_SMILE_PARSER_FEATURE_FLAGS;
        _formatGeneratorFeatures = SmileFactory.DEFAULT_SMILE_GENERATOR_FEATURE_FLAGS;
    }

    public SmileFactoryBuilder(SmileFactory base) {
        super(base);
        _formatParserFeatures = base._formatParserFeatures;
        _formatGeneratorFeatures = base._formatGeneratorFeatures;
    }

    // // // Parser features

    public SmileFactoryBuilder with(SmileParser.Feature f) {
        _formatParserFeatures |= f.getMask();
        return _this();
    }

    public SmileFactoryBuilder with(SmileParser.Feature first, SmileParser.Feature... other) {
        _formatParserFeatures |= first.getMask();
        for (SmileParser.Feature f : other) {
            _formatParserFeatures |= f.getMask();
        }
        return _this();
    }

    public SmileFactoryBuilder without(SmileParser.Feature f) {
        _formatParserFeatures &= ~f.getMask();
        return _this();
    }

    public SmileFactoryBuilder without(SmileParser.Feature first, SmileParser.Feature... other) {
        _formatParserFeatures &= ~first.getMask();
        for (SmileParser.Feature f : other) {
            _formatParserFeatures &= ~f.getMask();
        }
        return _this();
    }

    public SmileFactoryBuilder set(SmileParser.Feature f, boolean state) {
        return state ? with(f) : without(f);
    }
    
    // // // Generator features

    public SmileFactoryBuilder with(SmileGenerator.Feature f) {
        _formatGeneratorFeatures |= f.getMask();
        return _this();
    }

    public SmileFactoryBuilder with(SmileGenerator.Feature first, SmileGenerator.Feature... other) {
        _formatGeneratorFeatures |= first.getMask();
        for (SmileGenerator.Feature f : other) {
            _formatGeneratorFeatures |= f.getMask();
        }
        return _this();
    }

    public SmileFactoryBuilder without(SmileGenerator.Feature f) {
        _formatGeneratorFeatures &= ~f.getMask();
        return _this();
    }
    
    public SmileFactoryBuilder without(SmileGenerator.Feature first, SmileGenerator.Feature... other) {
        _formatGeneratorFeatures &= ~first.getMask();
        for (SmileGenerator.Feature f : other) {
            _formatGeneratorFeatures &= ~f.getMask();
        }
        return _this();
    }

    public SmileFactoryBuilder set(SmileGenerator.Feature f, boolean state) {
        return state ? with(f) : without(f);
    }

    // // // Accessors

    public int formatParserFeaturesMask() { return _formatParserFeatures; }
    public int formatGeneratorFeaturesMask() { return _formatGeneratorFeatures; }

    @Override
    public SmileFactory build() {
        // 28-Dec-2017, tatu: No special settings beyond base class ones, so:
        return new SmileFactory(this);
    }
}