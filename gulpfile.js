var gulp = require('gulp');
var watch = require('gulp-watch');
var plumber = require('gulp-plumber');
var concat = require('gulp-concat');
var sourcemaps = require('gulp-sourcemaps');
var uglify = require('gulp-uglify');
var ngAnnotate = require('gulp-ng-annotate');
var del = require('del');

/**
 * The angular application / css / view must be written in '/src/main/resources/angular-app/'
 * So gulp will package and copy it to '/src/main/webapp/' to enable hotswap
 * Then on maven validate phase, the directory '/src/main/webapp/' will be copyed to '/target/classes/static'
 */
var angularRoot = './src/main/resources/angular-app/';
var jsEntryPointPath = angularRoot + 'scripts/app.js';
var jsPaths = [angularRoot + 'scripts/**/*.module.js', angularRoot + 'scripts/**/*.js'];
var viewsPaths = [angularRoot + '**/*.html'];
var stylesPaths = [angularRoot + 'styles/*.css', angularRoot + 'scripts/**/*.css'];
var libPaths = angularRoot + 'libs/**/*.*';
var distPath = './src/main/webapp/';


gulp.task('js', function() {
    return gulp.src(jsPaths)
        .pipe(plumber())
        .pipe(ngAnnotate())
        .pipe(sourcemaps.init())
        .pipe(concat('bundle.js'))
        .pipe(ngAnnotate())
        .pipe(uglify())
        .pipe(sourcemaps.write())
        .pipe(gulp.dest(distPath + 'scripts'));
});

gulp.task('views', function() {
    return gulp.src(viewsPaths)
        .pipe(plumber())
        .pipe(gulp.dest(distPath));
});

gulp.task('styles', function() {
    return gulp.src(stylesPaths)
        .pipe(plumber())
        .pipe(gulp.dest(distPath + 'styles'));
});

gulp.task('libs', function() {
    return gulp.src(libPaths)
        .pipe(plumber())
        .pipe(gulp.dest(distPath + 'libs/'));
});

gulp.task('clean', function() {
    return del(distPath + '**/*');
});

gulp.task('build', ['clean'], function() {
    return gulp.start('js').start('views').start('styles').start('libs');
});


gulp.task('watch', ['js', 'views', 'styles', 'libs'], function() {
    watch(jsPaths, function() {
        gulp.start(['js']);
    });
    watch(viewsPaths, function() {
        gulp.start(['views']);
    });
    watch(stylesPaths, function() {
        gulp.start(['styles']);
    });
    watch(libPaths, function() {
        gulp.start(['libs']);
    });
});
