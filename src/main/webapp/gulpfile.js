var gulp = require('gulp'),
    plugins = require('gulp-load-plugins')({
        pattern: ['gulp-*', 'gulp.*'],
        replaceString: /\bgulp[\-.]/,
         rename: {
            'gulp-ruby-sass': 'sass'
        }
    }),
    browsersync = require('browser-sync');


var src = 'src/',
    build = 'build/';

gulp.task('sass', function() {
    return plugins.sass(src + 'scss/*.scss', {
            style: 'compressed'
        })
        .pipe(plugins.autoprefixer({
            browsers: ['last 4 versions'],
            cascade: false
        }))
        .pipe(gulp.dest(build + 'css/'));
});

gulp.task('imagemin', function() {
    return gulp.src(src + 'images/*')
        .pipe(plugins.imagemin({
            optimizationLevel: 4,
            progressive: true
        }))
        .pipe(gulp.dest(build + 'images/'));
});


gulp.task('iconmin', function() {
    return gulp.src(src + 'icons/*')
        .pipe(plugins.imagemin({
            optimizationLevel: 4,
            progressive: true
        }))
        .pipe(gulp.dest(build + 'icons/'));
});

gulp.task('eslint', function() {
    var eslint = plugins.eslint;
    return gulp.src(src + 'js/*.js')
        .pipe(eslint())
        .pipe(eslint.format())
        .pipe(eslint.failOnError());
});

gulp.task('scripts', function() {
    return gulp.src(src + 'js/*.js')
        // .pipe(plugins.order(['modernizr.js', 'main.js']))
        .pipe(plugins.concat('main.js'))
        .pipe(plugins.uglify())
        .pipe(plugins.rename({
            suffix: '.min'
        }))
        .pipe(gulp.dest(build + 'js/'));
});

gulp.task('bsync', function(){
    browsersync.init([build + 'css/*.css', build + 'js/*.js'], {
        server: {
            baseDir: './'
        }
    });
});

gulp.task('watch', function() {
    gulp.watch(src + 'scss/*.scss', ['sass', 'bsync']);
    gulp.watch(src + 'images/*', ['imagemin']);
    gulp.watch(src + 'js/*.js', ['eslint', 'scripts', 'bsync']);
    gulp.watch(src + 'icons/*', ['iconmin']);
});

gulp.task('default', ['sass', 'imagemin', 'iconmin', 'scripts', 'eslint', 'watch']);