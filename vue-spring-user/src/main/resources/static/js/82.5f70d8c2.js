"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[82],{9280:function(e,t,r){r.d(t,{Hk:function(){return d},J7:function(){return o},VM:function(){return s},gK:function(){return l},yK:function(){return i}});var a=r(3076),n=r(5739);function i(e){return a.Z.post("/api/frees",e,n.np).then((({data:{data:e}})=>e)).catch((({response:{data:{errorFields:e,errorMessage:t}}})=>{throw{data:e,message:t}}))}function s(e){return a.Z.get("/api/frees",{params:e}).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function o(e){return a.Z.get(`/api/frees/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function l(e,t){return a.Z.put(`/api/frees/${e}`,t,n.np).then((()=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function d(e){return a.Z.delete(`/api/frees/${e}`).then((()=>{})).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}},6950:function(e,t,r){r.d(t,{K:function(){return n}});r(6229),r(7330),r(2062);var a=r(3076);function n(e){return a.Z.get(`/api/file/board/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}},7050:function(e,t,r){function a(e,t=10){const{page:r,fromDate:a,toDate:n,searchCategory:i,search:s,limit:o,orderCondition:l,order:d}=e,c={};return c.page=r||1,c.fromDate=a||"",c.toDate=n||"",c.search=s||"",c.searchCategory=i||"",c.limit=o||t,c.orderCondition=l||"",c.order=d||"desc",c}r.d(t,{C:function(){return a}})},266:function(e,t,r){r.d(t,{Z:function(){return f}});var a=r(3396),n=r(7139);const i={class:"file-list-container"},s=["src"],o=["href"];function l(e,t,r,l,d,c){const u=(0,a.up)("font-awesome-icon"),p=(0,a.up)("base-button");return(0,a.wg)(),(0,a.iD)("div",i,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(r.fileList,(e=>((0,a.wg)(),(0,a.iD)("div",{key:e.fileId,class:"file-container"},[null!=e.galleryThumbName?((0,a.wg)(),(0,a.iD)("img",{key:0,src:`${d.dynamicDomain}/api/file/image/${e.galleryThumbName}`,alt:"썸네일 이미지",class:"file-thumb"},null,8,s)):((0,a.wg)(),(0,a.j4)(u,{key:1,icon:"paperclip",class:"file-icon"})),(0,a._)("a",{href:`${d.dynamicDomain}/api/file/${e.fileId}`,class:"file-link"},(0,n.zw)(e.fileOrgName),9,o),r.isUpdate?((0,a.wg)(),(0,a.j4)(p,{key:2,onClick:t=>c.onDelete(e.fileId),class:"file-btn-delete",name:"X"},null,8,["onClick"])):(0,a.kq)("",!0)])))),128))])}var d=r(8518),c={name:"FileList",components:{BaseButton:d.Z},data(){return{dynamicDomain:"http://52.78.57.232:8080"}},props:{fileList:{type:Array,required:!0,description:"게시글 첨부파일 목록"},isUpdate:{type:Boolean,default:!1,required:!1,description:"업데이트의 경우 삭제버튼 등장"}},methods:{onDelete(e){this.$emit("delete-file",e)}}},u=r(89);const p=(0,u.Z)(c,[["render",l],["__scopeId","data-v-232c98fc"]]);var f=p},8282:function(e,t,r){r.d(t,{Z:function(){return u}});r(7658);var a=r(3396);const n={class:"board-detail-btn-container"};function i(e,t,r,i,s,o){const l=(0,a.up)("base-button");return(0,a.wg)(),(0,a.iD)("div",n,[o.isCurrentUserId(r.board.userId)?((0,a.wg)(),(0,a.j4)(l,{key:0,onClick:t[0]||(t[0]=e=>this.$router.push({path:`${r.path}/update/${r.board.boardId}`,query:r.condition})),class:"board-detail-btn-update",name:"수정"})):(0,a.kq)("",!0),(0,a.Wm)(l,{onClick:t[1]||(t[1]=e=>this.$router.push({path:`${r.path}`,query:r.condition})),class:"board-detail-btn-list",name:"목록"}),o.isCurrentUserId(r.board.userId)?((0,a.wg)(),(0,a.j4)(l,{key:1,onClick:o.onDelete,class:"board-detail-btn-delete",name:"삭제"},null,8,["onClick"])):(0,a.kq)("",!0)])}var s=r(8518),o=r(6212),l={name:"BoardDetailBtnContainer",components:{BaseButton:s.Z},props:{condition:{type:Object,required:!0,description:"검색조건"},path:{type:String,required:!0,description:"게시글 경로"},board:{type:Object,default:void 0,required:!0,description:"게시글"}},methods:{isCurrentUserId:o.wu,onDelete(){confirm("삭제 하시겠습니까?")&&this.$emit("delete-board")}}},d=r(89);const c=(0,d.Z)(l,[["render",i],["__scopeId","data-v-889ba420"]]);var u=c},1432:function(e,t,r){r.d(t,{Z:function(){return c}});var a=r(3396),n=r(7139);const i={class:"board-detail-content"};function s(e,t,r,s,o,l){return(0,a.wg)(),(0,a.iD)("div",i,(0,n.zw)(r.board.boardContent),1)}var o={name:"BoardDetailContent",props:{board:{type:Object,required:!0,description:"게시글 정보"}}},l=r(89);const d=(0,l.Z)(o,[["render",s],["__scopeId","data-v-334ae2ce"]]);var c=d},5134:function(e,t,r){r.d(t,{Z:function(){return v}});var a=r(3396),n=r(7139);const i={class:"board-detail-header-container"},s={key:0,class:"board-detail-category"},o={class:"board-detail-title-container"},l={class:"board-detail-title"},d={key:0,class:"board-detail-title-answer"},c={class:"board-detail-regdate"},u={class:"board-detail-username"},p={class:"board-detail-viewcnt"};function f(e,t,r,f,y,h){return(0,a.wg)(),(0,a.iD)(a.HY,null,[(0,a._)("div",i,[r.isQna?(0,a.kq)("",!0):((0,a.wg)(),(0,a.iD)("div",s,(0,n.zw)(r.board.categoryName),1)),(0,a._)("div",o,[(0,a._)("div",l,(0,n.zw)(r.board.boardTitle),1),r.isQna?((0,a.wg)(),(0,a.iD)("span",d,(0,n.zw)(h.answerStatus),1)):(0,a.kq)("",!0)]),(0,a._)("div",c,(0,n.zw)(r.board.createDate),1),(0,a._)("div",u,(0,n.zw)(r.board.userName),1)]),(0,a._)("div",p,"조회수 : "+(0,n.zw)(r.board.viewCnt),1)],64)}var y={name:"BoardDetailHeader",props:{board:{type:Object,required:!0,description:"게시글 정보"},isQna:{type:Boolean,default:!1,required:!1,description:"문의게시글 여부"}},computed:{answerStatus(){return this.isQna&&this.board.answerStatus?"(답변완료)":"(미답변)"}}},h=r(89);const m=(0,h.Z)(y,[["render",f],["__scopeId","data-v-420fa16b"]]);var v=m},5082:function(e,t,r){r.d(t,{Z:function(){return d}});var a=r(3396),n=r(7139);function i(e,t,r,i,s,o){return(0,a.wg)(),(0,a.iD)("header",null,[(0,a._)("h1",null,(0,n.zw)(r.title),1)])}var s={name:"BoardTitle",props:{title:{type:String,required:!0,description:"게시글 타이틀"}}},o=r(89);const l=(0,o.Z)(s,[["render",i],["__scopeId","data-v-71f638fd"]]);var d=l},7082:function(e,t,r){r.r(t),r.d(t,{default:function(){return T}});r(7658);var a=r(3396),n=r(1432),i=r(5134),s=r(5082),o=r(2483),l=r(4870),d=r(7050),c=r(6170),u=r(9280),p=r(3076);function f(e){return p.Z.get(`/api/replies/board/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function y(e){return p.Z.post("/api/replies",e).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function h(e){return p.Z.delete(`/api/replies/${e}`).then().catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}var m=r(6950),v=r(266),g=r(7139);const b=e=>((0,a.dD)("data-v-dcd18d38"),e=e(),(0,a.Cn)(),e),w={class:"reply-container"},D={class:"reply-list-container"},C={class:"reply-left-container"},k={class:"reply-writer-date-container"},Z={class:"reply-writer"},_={class:"reply-date"},I={class:"reply-content"},$=["onClick"],q=b((()=>(0,a._)("div",{class:"reply-delete"},"삭제",-1))),B=[q],H={key:0,class:"reply-register-container"};function z(e,t,r,n,i,s){const o=(0,a.up)("base-input"),l=(0,a.up)("base-button");return(0,a.wg)(),(0,a.iD)("div",w,[(0,a._)("div",D,[((0,a.wg)(!0),(0,a.iD)(a.HY,null,(0,a.Ko)(r.replyList,(e=>((0,a.wg)(),(0,a.iD)("div",{key:e.replyId,class:"reply"},[(0,a._)("div",C,[(0,a._)("div",k,[(0,a._)("div",Z,(0,g.zw)(e.userName),1),(0,a._)("div",_,(0,g.zw)(e.createDate),1)]),(0,a._)("div",I,(0,g.zw)(e.replyContent),1)]),s.isCurrentUserId(e.userId)?((0,a.wg)(),(0,a.iD)("div",{key:0,onClick:t=>s.onDelete(e.replyId),class:"reply-right-container"},B,8,$)):(0,a.kq)("",!0)])))),128))]),s.isAuthenticated()?((0,a.wg)(),(0,a.iD)("div",H,[(0,a.Wm)(o,{modelValue:i.replyContent,"onUpdate:modelValue":t[0]||(t[0]=e=>i.replyContent=e),placeholder:i.replyPlaceHolder,class:"reply-register-input"},null,8,["modelValue","placeholder"]),(0,a.Wm)(l,{onClick:s.onRegister,name:"등록",class:"reply-register-button"},null,8,["onClick"])])):(0,a.kq)("",!0)])}var j=r(6212),M=r(8518),W=r(1379),R={name:"Reply",components:{BaseInput:W.Z,BaseButton:M.Z},data(){return{replyContent:"",replyError:"",replyPlaceHolder:"댓글을 입력해 주세요."}},props:{replyList:{type:Array,required:!0,description:"댓글 목록"}},methods:{isCurrentUserId:j.wu,isAuthenticated:j.$8,onRegister(){if(console.log((0,j.$8)()),this.validateReply()){const e={boardId:this.$route.params.boardId,replyContent:this.replyContent};y(e).then((e=>{this.$emit("register-reply",e),this.replyContent=""})).catch((({message:e})=>{console.error(e),alert(e)}))}},async onDelete(e){if(confirm("댓글을 삭제하시겠습니까?"))try{await h(e),this.$emit("delete-reply",e)}catch({message:t}){console.error(t)}},validateReply(){return!!this.replyContent||(alert("댓글을 입력해주세요."),!1)}}},U=r(89);const K=(0,U.Z)(R,[["render",z],["__scopeId","data-v-dcd18d38"]]);var N=K,O=r(8282),L=r(4231);const S={name:"FreeDetail"};var A=Object.assign(S,{setup(e){const t=(0,o.yj)(),r=(0,o.tv)(),p=(0,l.iH)({}),y=(0,l.iH)({}),h=(0,l.iH)([]),g=(0,l.iH)([]);async function b(){p.value=(0,d.C)(t.query);try{const e=t.params.boardId,[r,a,n]=await Promise.all([(0,u.J7)(e),(0,m.K)(e),f(e)]);y.value=r,y.value.createDate=(0,c.p)(y.value.createDate),h.value=a,g.value=n,g.value.forEach((e=>e.createDate=(0,c.p)(e.createDate)))}catch({message:e}){console.error(e)}}function w(e){e.createDate=(0,c.p)(e.createDate),g.value.push(e)}function D(e){g.value=g.value.filter((t=>t.replyId!==e))}async function C(){try{await(0,u.Hk)(t.params.boardId),r.push({path:"/frees",query:p.value})}catch({message:e}){alert(e)}}return b(),(e,t)=>((0,a.wg)(),(0,a.iD)(a.HY,null,[(0,a.Wm)(L.Z),(0,a.Wm)(s.Z,{title:"자유게시판"}),(0,a.Wm)(i.Z,{board:y.value},null,8,["board"]),(0,a.Wm)(n.Z,{board:y.value,class:"free-detail-content"},null,8,["board"]),(0,a.Wm)(v.Z,{"file-list":h.value,class:"free-detail-file-list"},null,8,["file-list"]),(0,a.Wm)(N,{onRegisterReply:w,onDeleteReply:D,"reply-list":g.value},null,8,["reply-list"]),(0,a.Wm)(O.Z,{onDeleteBoard:C,board:y.value,condition:p.value,path:"/frees",class:"free-detail-btn-container"},null,8,["board","condition"])],64))}});const Q=(0,U.Z)(A,[["__scopeId","data-v-52a8c690"]]);var T=Q}}]);
//# sourceMappingURL=82.5f70d8c2.js.map