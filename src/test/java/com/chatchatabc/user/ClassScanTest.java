package com.chatchatabc.user;

import com.chatchatabc.user.domain.repository.UserRepository;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ScanResult;
import org.jboss.jandex.AnnotationInstance;
import org.jboss.jandex.Index;
import org.jboss.jandex.Indexer;
import org.junit.jupiter.api.Test;
import org.mvnsearch.microservices.annotator.DatabaseAccess;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;


public class ClassScanTest {
    @Test
    public void testScanWithReflections() throws Exception {
        Reflections reflections = new Reflections("com.chatchatabc.user");
        Set<Class<?>> dbAccessClasses = reflections.get(SubTypes.of(TypesAnnotated.with(DatabaseAccess.class)).asClass());
        for (Class<?> remoteAccessClass : dbAccessClasses) {
            System.out.println(remoteAccessClass.getCanonicalName());
        }
    }

    @Test
    public void testScanWithClassGraph() {
        String pkg = "com.chatchatabc.user";
        String annotationClass = "org.mvnsearch.microservices.annotator.DatabaseAccess";
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()         // Scan classes, methods, fields, annotations
                .acceptPackages(pkg)     // Scan package and subpackages (omit to scan all packages)
                .scan()) {               // Start the scan
            for (ClassInfo classInfo : scanResult.getClassesWithAnnotation(annotationClass)) {
                System.out.println(classInfo.getName());
            }
        }
    }

    @Test
    public void testScanWithJanex() throws Exception {
        Indexer indexer = new Indexer();
        indexer.indexClass(UserRepository.class);
        final Index index = indexer.complete();
        final Collection<AnnotationInstance> annotationInstances = index.getAnnotations(DatabaseAccess.class);
        for (AnnotationInstance annotationInstance : annotationInstances) {
            System.out.println(annotationInstance.target().asClass().name());
        }
    }
}
