package net.modgarden.flowerbed.annotation;

import java.lang.annotation.*;

/**
 * Metadata for Mixins/patches used by Flowerbed.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.PACKAGE})
public @interface PatchMetadata {
	/**
	 * A lowercase alphanumeric & underscored identifier of the patch.
	 */
	String id();

	/**
	 * A description of the patch's purpose and function.
	 */
	String description();
}
