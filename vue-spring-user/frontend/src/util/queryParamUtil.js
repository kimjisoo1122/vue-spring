/**
 * 라우트쿼리로부터 검색조건을 설정합니다.
 *
 * @param routeQuery route.query 라우트쿼리
 * @param defaultLimit 기본 limit 10
 * @return condition 검색조건
 */
export function createCondition(routeQuery, defaultLimit = 10) {
    const {page, fromDate, toDate, searchCategory, search, limit, orderCondition, order} = routeQuery;
    const condition = {};
    condition.page = page || 1;
    condition.fromDate = fromDate || '';
    condition.toDate = toDate || '';
    condition.search = search || '';
    condition.searchCategory = searchCategory || '';
    condition.limit = limit || defaultLimit;
    condition.orderCondition = orderCondition || '';
    condition.order = order || 'desc';

    return condition;
}