'use strict';

var mbgl = require('../../..');
var suite = require('mapbox-gl-test-suite');
var request = require('request');

var tests;

if (process.argv[1] === __filename && process.argv.length > 2) {
    tests = process.argv.slice(2);
}

suite.run('native', { tests: tests }, function(style, options, callback) {
    var map = new mbgl.Map({
        request: function(req, callback) {
            request(req.url, { encoding: null }, function(err, response, body) {
                callback(err, { data: body });
            });
        },
        view: new mbgl.View({
            width: options.width,
            height: options.height,
            ratio: options.pixelRatio
        })
    });

    map.load(style);
    map.render(options, function(err, pixels) {
        map.release();
        callback(err, pixels);
    });
});
