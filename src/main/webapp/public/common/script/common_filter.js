web.filter('startFrom', function() 
{
    return function(input, start) 
    {
        if (!input || !input.length) { return; }
        
        start = +start; //parse to int
        return input.slice(start);
    }
});


web.directive('csPopover', function ()
{
    return {
        restrict: 'A',
        template: '<span>{{label}}</span>',
        link: function (scope, el, attrs)
        {
            scope.label = attrs.popoverLabel;
            $(el).popover({
                trigger: 'click',
                html: true,
                content: attrs.popoverHtml,
                placement: attrs.popoverPlacement
            });
        }
    };
});



web.directive('match', function () 
{
	return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl)
        {
            var firstPassword = '#'+attrs.match;
            elem.add(firstPassword).on('keyup', function () 
            {
                scope.$apply(function () 
                {
                	ctrl.$setValidity('match', elem.val() === $(firstPassword).val());
                });
            });
        }
    }
});