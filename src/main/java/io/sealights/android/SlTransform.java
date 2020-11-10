package io.sealights.android;

import com.android.build.api.transform.*;
import com.google.common.collect.Sets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

class SlTransform extends Transform {
    @Override
    public String getName() {
        return "sealights";
    }

    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return Sets.newHashSet(QualifiedContent.DefaultContentType.CLASSES);
    }

    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return Sets.newHashSet(QualifiedContent.Scope.PROJECT, QualifiedContent.Scope.SUB_PROJECTS);
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {

//        boolean applyTransform = transformInvocation.getContext().getVariantName().equals("debug");

        for (TransformInput input : transformInvocation.getInputs()) {
            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
                File dirOutput = transformInvocation.getOutputProvider().getContentLocation(
                        directoryInput.getName(), getOutputTypes(), getScopes(), Format.DIRECTORY);
                FileUtils.copyDirectory(directoryInput.getFile(), dirOutput);
            }

            for (JarInput jarInput : input.getJarInputs()) {
                File jarOutput = transformInvocation.getOutputProvider().getContentLocation(
                        jarInput.getName(), getOutputTypes(), getScopes(), Format.JAR);
                FileUtils.copyFile(jarInput.getFile(), jarOutput);
            }
        }
    }
}
