"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[264],{8232:function(e,a,t){t.d(a,{HI:function(){return o},L1:function(){return s},NB:function(){return u},Qz:function(){return i},zF:function(){return l}});var r=t(3076),n=t(5739);function o(e){return r.Z.post("/api/galleries",e,n.np).then((({data:{data:e}})=>e)).catch((({response:{data:{errorFields:e,errorMessage:a}}})=>{throw{data:e,message:a}}))}function l(e){return r.Z.get("/api/galleries",{params:e}).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function u(e){return r.Z.get(`/api/galleries/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function s(e,a){return r.Z.put(`/api/galleries/${e}`,a,n.np).then((()=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function i(e){return r.Z.delete(`/api/galleries/${e}`).then((()=>{})).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}},156:function(e,a,t){function r(e,a){for(const t in e)e[t]=a[t]}t.d(a,{M:function(){return r}})},8795:function(e,a,t){t.r(a),t.d(a,{default:function(){return j}});t(7658);var r=t(3396),n=t(4870),o=t(5082),l=t(4761),u=t(2384),s=t(3795),i=t(1379),c=t(4909),d=t(399),v=t(2308),m=t(2483),p=t(5739),g=t(7474),f=t(65),b=t(8232),h=t(6212),y=t(4231),Z=t(1037),C=t(156),I=t(7050);const W={class:"update-container"},w={class:"update-category-container"},F={class:"update-category-input-container"},H={class:"update-title-container"},_={class:"update-title-input-container"},T={class:"update-content-container"},U={class:"update-content-input-container"},V={class:"update-file-container"},q={class:"update-file-input-container"},M={name:"GalleryUpdate"};var S=Object.assign(M,{setup(e){const a=(0,f.oR)(),t=(0,m.yj)(),M=(0,m.tv)(),S=(0,n.iH)({categoryId:"",boardTitle:"",boardContent:""}),k=(0,n.iH)({categoryId:"",boardTitle:"",boardContent:"",saveFiles:""}),$=(0,n.iH)({}),j=(0,n.iH)([]),D=(0,n.iH)([]),L=(0,n.iH)({}),z=(0,n.iH)([]);async function B(){L.value=(0,I.C)(t.query,3);try{const e=t.params.boardId,[a,r]=await Promise.all([(0,b.NB)(e),(0,g.A)(p.ui)]);$.value=a.gallery,D.value=a.fileList,j.value=r,(0,C.M)(S.value,$.value)}catch({message:e}){console.error(e)}}async function N(){if(!E()&&!(0,h.wu)($.value.userId))return!1;try{const e=A(),r=await(0,b.L1)(t.params.boardId,e);a.commit("boardFileStore/clearFile"),M.push({path:`/galleries/${r}`,query:L.value})}catch({data:e,message:r}){for(const a in e)k.value[a]=e[a];console.error(r)}}function O(e){z.value.push(e),D.value=D.value.filter((a=>a.fileId!==e))}function A(){const e=new FormData;for(const a in S.value)e.append(a,S.value[a]);a.getters["boardFileStore/getSaveFiles"].forEach((a=>{e.append("saveFiles",a)}));for(const a of z.value)e.append("deleteFiles",a);return e}function E(){k.value.boardTitle=(0,c.uq)(S.value.boardTitle),k.value.boardContent=(0,c.ty)(S.value.boardContent),k.value.categoryId=(0,c.Hm)(S.value.categoryId);for(const e of Object.values(k.value))if(e)return!1;return!0}return B(),(e,a)=>((0,r.wg)(),(0,r.iD)(r.HY,null,[(0,r.Wm)(y.Z),(0,r.Wm)(o.Z,{title:"갤러리"}),(0,r._)("div",W,[(0,r._)("div",w,[(0,r.Wm)(l.Z,{name:"분류",class:"board-form-title-category"}),(0,r._)("div",F,[(0,r.Wm)(u.Z,{modelValue:S.value.categoryId,"onUpdate:modelValue":a[0]||(a[0]=e=>S.value.categoryId=e),onChange:a[1]||(a[1]=e=>k.value.categoryId=(0,n.SU)(c.Hm)(S.value.categoryId)),"category-list":j.value,class:"update-category"},null,8,["modelValue","category-list"]),(0,r.Wm)(s.Z,{"error-msg":k.value.categoryId},null,8,["error-msg"])])]),(0,r._)("div",H,[(0,r.Wm)(l.Z,{name:"제목",class:"board-form-title-title"}),(0,r._)("div",_,[(0,r.Wm)(i.Z,{modelValue:S.value.boardTitle,"onUpdate:modelValue":a[2]||(a[2]=e=>S.value.boardTitle=e),onChange:a[3]||(a[3]=e=>k.value.boardTitle=(0,n.SU)(c.uq)(S.value.boardTitle)),placeholder:"제목을 입력해 주세요.",class:"update-title"},null,8,["modelValue"]),(0,r.Wm)(s.Z,{"error-msg":k.value.boardTitle},null,8,["error-msg"])])]),(0,r._)("div",T,[(0,r.Wm)(l.Z,{name:"내용",class:"board-form-title-content"}),(0,r._)("div",U,[(0,r.Wm)(d.Z,{modelValue:S.value.boardContent,"onUpdate:modelValue":a[4]||(a[4]=e=>S.value.boardContent=e),onChange:a[5]||(a[5]=e=>k.value.boardContent=(0,n.SU)(c.uq)(S.value.boardContent)),class:"update-content"},null,8,["modelValue"]),(0,r.Wm)(s.Z,{"error-msg":k.value.boardContent},null,8,["error-msg"])])]),(0,r._)("div",V,[(0,r.Wm)(l.Z,{required:!1,name:"첨부",class:"board-form-title-file"}),(0,r._)("div",q,[(0,r.Wm)(v.Z,{onDeleteFile:O,"is-update":!0,"file-list":D.value},null,8,["file-list"]),(0,r.Wm)(s.Z,{"error-msg":k.value.saveFiles},null,8,["error-msg"])])]),(0,r.Wm)(Z.Z,{onUpdate:N,onCancel:a[6]||(a[6]=e=>{(0,n.SU)(M).push({path:"/galleries",query:L.value})}),"form-type":"update"})])],64))}}),k=t(89);const $=(0,k.Z)(S,[["__scopeId","data-v-ae3718ce"]]);var j=$}}]);
//# sourceMappingURL=264.66d8c4d3.js.map