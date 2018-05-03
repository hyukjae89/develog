var homeData = homeData || (function(){
    'use strict';

    var _post = {};

    var _getPost = function() {
        return this._post;
    };

    var _setPost = function(post) {
        this._post = post;
    };

    var _smartEditor = [];

    return {
        getPost : _getPost,
        setPost : _setPost,

        smartEditor : _smartEditor
    };

})();