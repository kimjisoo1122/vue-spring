"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[218],{7474:function(e,t,i){i.d(t,{A:function(){return r}});var n=i(3076);function r(e){return n.Z.get(`/api/categories/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}},4909:function(e,t,i){function n(e){return e?"":"카테고리는 필수입력 입니다."}function r(e){return e?e.length>=100?"100글자 미만 이여야 합니다.":"":"제목은 필수입력 입니다."}function a(e){return e?e.length>=4e3?"4000글자 미만 이여야 합니다.":void 0:"내용은 필수입력 입니다."}i.d(t,{Hm:function(){return n},ty:function(){return a},uq:function(){return r}})},7050:function(e,t,i){function n(e,t=10){const{page:i,fromDate:n,toDate:r,searchCategory:a,search:o,limit:l,orderCondition:s,order:d}=e,u={};return u.page=i||1,u.fromDate=n||"",u.toDate=r||"",u.search=o||"",u.searchCategory=a||"",u.limit=l||t,u.orderCondition=s||"",u.order=d||"desc",u}i.d(t,{C:function(){return n}})},2384:function(e,t,i){i.d(t,{Z:function(){return p}});var n=i(3396),r=i(7139);const a=e=>((0,n.dD)("data-v-61bbeed8"),e=e(),(0,n.Cn)(),e),o=["value"],l=a((()=>(0,n._)("option",{value:""},"전체 분류",-1))),s=["value"];function d(e,t,i,a,d,u){return(0,n.wg)(),(0,n.iD)("select",{onChange:t[0]||(t[0]=(...e)=>u.onChange&&u.onChange(...e)),value:i.modelValue},[l,((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(i.categoryList,(e=>((0,n.wg)(),(0,n.iD)("option",{key:e.categoryId,value:e.categoryId},(0,r.zw)(e.categoryName),9,s)))),128))],40,o)}var u={name:"CategorySelect",props:{modelValue:{type:String,required:!0,description:"입력 값"},categoryList:{type:Array,required:!0,description:"카테고리 목록"}},methods:{onChange(e){this.$emit("update:modelValue",e.target.value)}}},c=i(89);const f=(0,c.Z)(u,[["render",d],["__scopeId","data-v-61bbeed8"]]);var p=f},7211:function(e,t,i){i.d(t,{Z:function(){return p}});var n=i(3396),r=i(7139);const a={class:"file-list-container"},o=["src"],l=["href"];function s(e,t,i,s,d,u){const c=(0,n.up)("font-awesome-icon"),f=(0,n.up)("base-button");return(0,n.wg)(),(0,n.iD)("div",a,[((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(i.fileList,(e=>((0,n.wg)(),(0,n.iD)("div",{key:e.fileId,class:"file-container"},[null!=e.galleryThumbName?((0,n.wg)(),(0,n.iD)("img",{key:0,src:`/api/file/image/${e.galleryThumbName}`,alt:"썸네일 이미지",class:"file-thumb"},null,8,o)):((0,n.wg)(),(0,n.j4)(c,{key:1,icon:"paperclip",class:"file-icon"})),(0,n._)("a",{href:`/api/file/${e.fileId}`,class:"file-link"},(0,r.zw)(e.fileOrgName),9,l),i.isUpdate?((0,n.wg)(),(0,n.j4)(f,{key:2,onClick:t=>u.onDelete(e.fileId),class:"file-btn-delete",name:"X"},null,8,["onClick"])):(0,n.kq)("",!0)])))),128))])}var d=i(8518),u={name:"FileList",components:{BaseButton:d.Z},props:{fileList:{type:Array,required:!0,description:"게시글 첨부파일 목록"},isUpdate:{type:Boolean,default:!1,required:!1,description:"업데이트의 경우 삭제버튼 등장"}},methods:{onDelete(e){this.$emit("delete-file",e)}}},c=i(89);const f=(0,c.Z)(u,[["render",s],["__scopeId","data-v-66d6ae47"]]);var p=f},399:function(e,t,i){i.d(t,{Z:function(){return d}});var n=i(3396);const r=["value"];function a(e,t,i,a,o,l){return(0,n.wg)(),(0,n.iD)("textarea",{value:i.modelValue,onChange:t[0]||(t[0]=(...e)=>l.onChange&&l.onChange(...e))},"\r\n  ",40,r)}var o={name:"BaseTextarea",props:{modelValue:{type:String,default:"",required:!1,description:"양방향 값"}},methods:{onChange(e){this.$emit("update:modelValue",e.target.value)}}},l=i(89);const s=(0,l.Z)(o,[["render",a],["__scopeId","data-v-6b6df427"]]);var d=s},2308:function(e,t,i){i.d(t,{Z:function(){return I}});var n=i(3396),r=i(7139);const a={class:"board-file-list-container"},o={class:"allowed-file-msg"};function l(e,t,i,l,s,d){const u=(0,n.up)("file-list"),c=(0,n.up)("base-file-input");return(0,n.wg)(),(0,n.iD)("div",a,[(0,n._)("div",o,(0,r.zw)(d.allowedFileMsg),1),(0,n.Wm)(u,{onDeleteFile:t[0]||(t[0]=t=>e.$emit("delete-file",t)),"file-list":i.fileList,"is-update":i.isUpdate},null,8,["file-list","is-update"]),((0,n.wg)(!0),(0,n.iD)(n.HY,null,(0,n.Ko)(d.addInputSize,(e=>((0,n.wg)(),(0,n.j4)(c,{key:e,"file-id":e,"allowed-extensions":s.allowedExtensions,"allowed-file-size":s.allowedFileSize},null,8,["file-id","allowed-extensions","allowed-file-size"])))),128))])}var s=i(9242);const d={class:"file-input-container"},u=["src"],c=["for"],f=["id"];function p(e,t,i,r,a,o){const l=(0,n.up)("input-error");return(0,n.wg)(),(0,n.iD)("div",d,[a.fileThumbSrc?((0,n.wg)(),(0,n.iD)("img",{key:0,src:a.fileThumbSrc,alt:"업로드 파일 썸네일",class:"file-thumb"},null,8,u)):(0,n.kq)("",!0),(0,n.wy)((0,n._)("input",{"onUpdate:modelValue":t[0]||(t[0]=e=>a.fileName=e),type:"text",class:"file-input-disabled",disabled:""},null,512),[[s.nr,a.fileName]]),(0,n._)("label",{for:i.fileId,class:"file-upload-label"},"파일 찾기",8,c),(0,n._)("input",{onChange:t[1]||(t[1]=(...e)=>o.onUpload&&o.onUpload(...e)),id:i.fileId,type:"file",class:"file-input"},null,40,f),(0,n.Wm)(l,{"error-msg":a.fileError,class:"file-error"},null,8,["error-msg"])])}var m=i(3795),g={name:"BaseFileInput",components:{InputError:m.Z},data(){return{fileName:"",fileError:"",fileThumbSrc:""}},props:{allowedFileSize:{type:Number,default:2e6,required:!1,description:"파일 최대 크기"},fileId:{type:Number,required:!0,description:"파일 번호"},allowedExtensions:{type:Array,default:()=>["jpg","gif","png","zip"],required:!1,description:"허용 가능한 파일 확장자"}},methods:{onUpload(e){const t=e.target.files[0];return void 0===t?(this.$store.commit("boardFileStore/cancelFile",this.fileId),this.fileName="",!1):this.validateFile(t)?(this.showThumb(t,e.target.result),this.fileError="",t.fileId=this.fileId,this.fileName=t.name,this.$store.commit("boardFileStore/uploadFile",{fileId:this.fileId,file:t}),void this.$emit("upload-file",t)):(this.$store.commit("boardFileStore/cancelFile",this.fileId),!1)},validateFile(e){if(e.size>this.allowedFileSize){const e=this.allowedFileSize/1e6+"MB";return alert(`파일사이즈는 ${e}를 넘길수 없습니다.`),!1}const t=e.name.lastIndexOf("."),i=e.name.substring(t+1);return!!this.allowedExtensions.includes(i)||(alert("허용된 확장자가 아닙니다."),!1)},showThumb(e){const t=e.name.lastIndexOf("."),i=e.name.substring(t+1);if("zip"!==i){const t=new FileReader;t.onload=e=>{this.fileThumbSrc=e.target.result},t.readAsDataURL(e)}}}},h=i(89);const v=(0,h.Z)(g,[["render",p],["__scopeId","data-v-352ced7a"]]);var w=v,y=i(7211),b={name:"BoardFileList",components:{FileList:y.Z,BaseFileInput:w},data(){return{allowedExtensions:["jpg","gif","png","zip"],allowedFileSize:2e6}},props:{fileList:{type:Array,default:()=>[],required:!1,description:"게시글에 등록된 첨부파일 목록"},inputSize:{type:Number,default:5,required:!1,description:"새로 추가할 첨부파일 개수"},isUpdate:{type:Boolean,default:!1,required:!1,description:"업데이트의 경우 삭제버튼 등장"}},computed:{addInputSize(){const e=void 0===this.fileList?0:this.fileList.length;return this.inputSize-e},allowedFileMsg(){return`\n        ${this.allowedExtensions.join(", ")}파일만\n        파일사이즈${this.allowedFileSize/1e6}MB까지 업로드 가능합니다.`}}};const C=(0,h.Z)(b,[["render",l],["__scopeId","data-v-d75538e8"]]);var I=C},1037:function(e,t,i){i.d(t,{Z:function(){return u}});var n=i(3396);const r={class:"form-btn-container"};function a(e,t,i,a,o,l){const s=(0,n.up)("base-button");return(0,n.wg)(),(0,n.iD)("div",r,["register"===this.formType?((0,n.wg)(),(0,n.j4)(s,{key:0,onClick:l.onRegister,name:"등록",class:"register-btn-save"},null,8,["onClick"])):(0,n.kq)("",!0),"update"===this.formType?((0,n.wg)(),(0,n.j4)(s,{key:1,onClick:l.onUpdate,name:"수정",class:"update-btn-save"},null,8,["onClick"])):(0,n.kq)("",!0),(0,n.Wm)(s,{onClick:l.onCancel,name:"취소",class:"form-btn-cancel"},null,8,["onClick"])])}var o=i(8518),l={name:"BoardFormBtnContainer",components:{BaseButton:o.Z},props:{formType:{type:String,required:!0,description:"register 또는 update로 폼을 구별합니다."}},methods:{onRegister(){confirm("등록 하시겠습니까?")&&this.$emit("register")},onUpdate(){confirm("수정 하시겠습니까?")&&this.$emit("update")},onCancel(){confirm("취소 하시겠습니까?")&&this.$emit("cancel")}}},s=i(89);const d=(0,s.Z)(l,[["render",a],["__scopeId","data-v-1fca8b1e"]]);var u=d},4761:function(e,t,i){i.d(t,{Z:function(){return f}});var n=i(3396),r=i(7139);const a={class:"board-form-title-container"},o={class:"board-form-title-name"},l={key:0,class:"board-form-title-required-star"};function s(e,t,i,s,d,u){return(0,n.wg)(),(0,n.iD)("div",a,[(0,n._)("span",o,(0,r.zw)(i.name),1),i.required?((0,n.wg)(),(0,n.iD)("span",l,"*")):(0,n.kq)("",!0)])}var d={name:"BoardFormTitle",props:{name:{type:String,required:!0,description:"타이틀 이름"},required:{type:Boolean,default:!0,required:!1,description:"필수 여부"}}},u=i(89);const c=(0,u.Z)(d,[["render",s],["__scopeId","data-v-68bea405"]]);var f=c},5082:function(e,t,i){i.d(t,{Z:function(){return d}});var n=i(3396),r=i(7139);function a(e,t,i,a,o,l){return(0,n.wg)(),(0,n.iD)("header",null,[(0,n._)("h1",null,(0,r.zw)(i.title),1)])}var o={name:"BoardTitle",props:{title:{type:String,required:!0,description:"게시글 타이틀"}}},l=i(89);const s=(0,l.Z)(o,[["render",a],["__scopeId","data-v-71f638fd"]]);var d=s}}]);
//# sourceMappingURL=218.20e02691.js.map