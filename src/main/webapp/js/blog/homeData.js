var homeData = homeData || (function(){
    'use strict';

    var _posts = [];

    var _getPost = function(ord) {
        return this._posts[ord];
    };

    var _getPosts = function() {
        return this._posts;
    };

    var _setPost = function(ord, post) {
        this._posts[ord] = post;
    };


    var _smartEditor = [];

    return {
        getPost : _getPost,
        setPost : _setPost,

        smartEditor : _smartEditor
    };

})();