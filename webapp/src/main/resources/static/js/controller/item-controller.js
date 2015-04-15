'use strict';
angular.module('auctionApp')
.controller('ItemController', ['$scope', '$routeParams', 'ItemResource',
   function($scope, $routeParams, ItemResource) {
      $scope.item = ItemResource.get({itemId:$routeParams.itemId});
      $('#name').focus();
      $scope.saveItem = function() {
         $scope.item.$save();
      }
   }
]);
