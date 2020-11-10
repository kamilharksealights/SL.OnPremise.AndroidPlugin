package io.sealights.android;

import com.android.build.gradle.AppPlugin;
import com.android.build.gradle.BaseExtension;
import com.android.build.gradle.LibraryPlugin;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class SLPlugin implements Plugin<Project> {

    @Override
    public void apply(Project target) {
        boolean isAndroid = target.getPlugins().hasPlugin(AppPlugin.class) ||
                        target.getPlugins().hasPlugin(LibraryPlugin.class);
        if (!isAndroid) {
            throw new GradleException("'com.android.application' or 'com.android.library' plugin required.");
        }

        SLExtension extension = target.getExtensions().create("sealights", SLExtension.class);

        BaseExtension android = (BaseExtension) target.getExtensions().findByName("android");
        android.registerTransform(new SlTransform());
    }

}
