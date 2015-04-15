'use strict';
angular.module('auctionApp')
.controller('BidderListController', ['$scope', 'BidderResource',
   function ($scope, BidderResource) {
      $('#bidderQuery').focus();
      $scope.currentPage = 1;
      $scope.itemsPerPage = 10;
      $scope.bidders = BidderResource.query();
      $scope.bidderOnPage = function(index) {
         return (Math.floor(index/$scope.itemsPerPage)+1) == $scope.currentPage;
      }
      $scope.pageChanged = function() {
      }
   }
]);
