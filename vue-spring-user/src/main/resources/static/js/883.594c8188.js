"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[883],{1495:function(e,a,t){t.d(a,{JM:function(){return i},Qp:function(){return s},bH:function(){return d},nv:function(){return o},o8:function(){return n}});var r=t(3076);function n(e){return r.Z.post("/api/qna",e).then((({data:{data:e}})=>e)).catch((({response:{data:{errorFields:e,errorMessage:a}}})=>{throw{data:e,message:a}}))}function o(e){return r.Z.get("/api/qna",{params:e}).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function s(e){return r.Z.get(`/api/qna/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function d(e,a){return r.Z.put(`/api/qna/${e}`,a).then((()=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function i(e){return r.Z.delete(`/api/qna/${e}`).then((()=>{})).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}},7050:function(e,a,t){function r(e,a=10){const{page:t,fromDate:r,toDate:n,searchCategory:o,search:s,limit:d,orderCondition:i,order:c}=e,u={};return u.page=t||1,u.fromDate=r||"",u.toDate=n||"",u.search=s||"",u.searchCategory=o||"",u.limit=d||a,u.orderCondition=i||"",u.order=c||"desc",u}t.d(a,{C:function(){return r}})},8282:function(e,a,t){t.d(a,{Z:function(){return l}});t(7658);var r=t(3396);const n={class:"board-detail-btn-container"};function o(e,a,t,o,s,d){const i=(0,r.up)("base-button");return(0,r.wg)(),(0,r.iD)("div",n,[d.isCurrentUserId(t.board.userId)?((0,r.wg)(),(0,r.j4)(i,{key:0,onClick:a[0]||(a[0]=e=>this.$router.push({path:`${t.path}/update/${t.board.boardId}`,query:t.condition})),class:"board-detail-btn-update",name:"수정"})):(0,r.kq)("",!0),(0,r.Wm)(i,{onClick:a[1]||(a[1]=e=>this.$router.push({path:`${t.path}`,query:t.condition})),class:"board-detail-btn-list",name:"목록"}),d.isCurrentUserId(t.board.userId)?((0,r.wg)(),(0,r.j4)(i,{key:1,onClick:d.onDelete,class:"board-detail-btn-delete",name:"삭제"},null,8,["onClick"])):(0,r.kq)("",!0)])}var s=t(8518),d=t(6212),i={name:"BoardDetailBtnContainer",components:{BaseButton:s.Z},props:{condition:{type:Object,required:!0,description:"검색조건"},path:{type:String,required:!0,description:"게시글 경로"},board:{type:Object,default:void 0,required:!0,description:"게시글"}},methods:{isCurrentUserId:d.wu,onDelete(){confirm("삭제 하시겠습니까?")&&this.$emit("delete-board")}}},c=t(89);const u=(0,c.Z)(i,[["render",o],["__scopeId","data-v-889ba420"]]);var l=u},2260:function(e,a,t){t.d(a,{Z:function(){return u}});var r=t(3396),n=t(7139);const o={class:"board-detail-content"};function s(e,a,t,s,d,i){return(0,r.wg)(),(0,r.iD)("div",o,[(0,r._)("pre",null,(0,n.zw)(t.board.boardContent),1)])}var d={name:"BoardDetailContent",props:{board:{type:Object,required:!0,description:"게시글 정보"}}},i=t(89);const c=(0,i.Z)(d,[["render",s],["__scopeId","data-v-493e8908"]]);var u=c},5134:function(e,a,t){t.d(a,{Z:function(){return f}});var r=t(3396),n=t(7139);const o={class:"board-detail-header-container"},s={key:0,class:"board-detail-category"},d={class:"board-detail-title-container"},i={class:"board-detail-title"},c={key:0,class:"board-detail-title-answer"},u={class:"board-detail-regdate"},l={class:"board-detail-username"},p={class:"board-detail-viewcnt"};function b(e,a,t,b,v,w){return(0,r.wg)(),(0,r.iD)(r.HY,null,[(0,r._)("div",o,[t.isQna?(0,r.kq)("",!0):((0,r.wg)(),(0,r.iD)("div",s,(0,n.zw)(t.board.categoryName),1)),(0,r._)("div",d,[(0,r._)("div",i,(0,n.zw)(t.board.boardTitle),1),t.isQna?((0,r.wg)(),(0,r.iD)("span",c,(0,n.zw)(w.answerStatus),1)):(0,r.kq)("",!0)]),(0,r._)("div",u,(0,n.zw)(t.board.createDate),1),(0,r._)("div",l,(0,n.zw)(t.board.userName),1)]),(0,r._)("div",p,"조회수 : "+(0,n.zw)(t.board.viewCnt),1)],64)}var v={name:"BoardDetailHeader",props:{board:{type:Object,required:!0,description:"게시글 정보"},isQna:{type:Boolean,default:!1,required:!1,description:"문의게시글 여부"}},computed:{answerStatus(){return this.isQna&&this.board.answerStatus?"(답변완료)":"(미답변)"}}},w=t(89);const h=(0,w.Z)(v,[["render",b],["__scopeId","data-v-420fa16b"]]);var f=h},5082:function(e,a,t){t.d(a,{Z:function(){return c}});var r=t(3396),n=t(7139);function o(e,a,t,o,s,d){return(0,r.wg)(),(0,r.iD)("header",null,[(0,r._)("h1",null,(0,n.zw)(t.title),1)])}var s={name:"BoardTitle",props:{title:{type:String,required:!0,description:"게시글 타이틀"}}},d=t(89);const i=(0,d.Z)(s,[["render",o],["__scopeId","data-v-71f638fd"]]);var c=i},4883:function(e,a,t){t.r(a),t.d(a,{default:function(){return z}});t(7658);var r=t(3396),n=t(7139),o=t(2260),s=t(5134),d=t(5082),i=t(2483),c=t(4870),u=t(7050),l=t(6170),p=t(8282),b=t(1495),v=t(4521);t(6212);const w=e=>((0,r.dD)("data-v-643879dd"),e=e(),(0,r.Cn)(),e),h={key:0,class:"qna-detail-answer-container"},f={class:"qna-answer-header-container"},m={class:"qna-answer-name"},g={class:"qna-answer-regdate"},q={class:"qna-answer-content"},y={key:1,class:"qna-answer-empty-container"},D=w((()=>(0,r._)("div",{class:"qna-answer-empty"},"아직 등록된 답변이 없습니다.",-1))),_=[D],C={name:"QnaDetail"};var Z=Object.assign(C,{setup(e){const a=(0,i.yj)(),t=(0,i.tv)(),w=(0,c.iH)({}),D=(0,c.iH)({});async function C(){w.value=(0,u.C)(a.query),w.value.myQna=a.query.myQna||!1;try{const e=a.params.boardId,[t]=await Promise.all([(0,b.Qp)(e)]);D.value=t,D.value.createDate=(0,l.p)(D.value.createDate)}catch({message:e}){console.error(e)}}async function Z(){try{await(0,b.JM)(a.params.boardId),t.push({path:"/qna",query:w})}catch({message:e}){alert(e)}}return C(),(e,a)=>((0,r.wg)(),(0,r.iD)(r.HY,null,[(0,r.Wm)(v.Z),(0,r.Wm)(d.Z,{title:"문의 게시판"}),(0,r.Wm)(s.Z,{"is-qna":!0,board:D.value},null,8,["board"]),(0,r.Wm)(o.Z,{board:D.value,class:"qna-detail-content"},null,8,["board"]),D.value.answerStatus?((0,r.wg)(),(0,r.iD)("div",h,[(0,r._)("div",f,[(0,r._)("span",m,(0,n.zw)(D.value.answerWriter),1),(0,r._)("span",g,(0,n.zw)(D.value.createDate),1)]),(0,r._)("div",q,(0,n.zw)(D.value.qnaAnswer),1)])):((0,r.wg)(),(0,r.iD)("div",y,_)),(0,r.Wm)(p.Z,{onDeleteBoard:Z,board:D.value,condition:w.value,path:"/qna",class:"qna-detail-btn-container"},null,8,["board","condition"])],64))}}),k=t(89);const I=(0,k.Z)(Z,[["__scopeId","data-v-643879dd"]]);var z=I}}]);
//# sourceMappingURL=883.594c8188.js.map