'use strict';
angular.module('auctionApp')
.controller('ItemListController', ['$scope', 'ItemResource',
   function ($scope, ItemResource) {
      $('#itemQuery').focus();
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.items = ItemResource.query();
      $scope.itemOnPage = function(index) {
         return (Math.floor(index/$scope.itemsPerPage)+1) == $scope.currentPage;
      }
   }
]);
