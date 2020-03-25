$('.card').mouseenter(function () {
    $(this).addClass('mouse');
});

$('.card').mouseleave(function () {
    $(this).removeClass('mouse');
});

$('#ticketTypePremium').click(function () {
    $('#ticketType').val('P');
    $('#ticketTypePremium').addClass('selected');
    $('#ticketTypeStandard').removeClass('selected');
    $('#ticketTypeCommunity').removeClass('selected');
});

$('#ticketTypeStandard').click(function () {
    $('#ticketType').val('S');
    $('#ticketTypePremium').removeClass('selected');
    $('#ticketTypeStandard').addClass('selected');
    $('#ticketTypeCommunity').removeClass('selected');
});

$('#ticketTypeCommunity').click(function () {
    $('#ticketType').val('C');
    $('#ticketTypePremium').removeClass('selected');
    $('#ticketTypeStandard').removeClass('selected');
    $('#ticketTypeCommunity').addClass('selected');
});
