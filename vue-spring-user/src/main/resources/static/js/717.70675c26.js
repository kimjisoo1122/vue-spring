"use strict";(self["webpackChunkfrontend"]=self["webpackChunkfrontend"]||[]).push([[717],{8232:function(e,a,r){r.d(a,{HI:function(){return o},L1:function(){return i},NB:function(){return s},Qz:function(){return u},zF:function(){return l}});var t=r(3076),n=r(5739);function o(e){return t.Z.post("/api/galleries",e,n.np).then((({data:{data:e}})=>e)).catch((({response:{data:{errorFields:e,errorMessage:a}}})=>{throw{data:e,message:a}}))}function l(e){return t.Z.get("/api/galleries",{params:e}).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function s(e){return t.Z.get(`/api/galleries/${e}`).then((({data:{data:e}})=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function i(e,a){return t.Z.put(`/api/galleries/${e}`,a,n.np).then((()=>e)).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}function u(e){return t.Z.delete(`/api/galleries/${e}`).then((()=>{})).catch((({response:{data:{errorMessage:e}}})=>{throw{message:e}}))}},6597:function(e,a,r){r.r(a),r.d(a,{default:function(){return k}});r(7658);var t=r(3396),n=r(4870),o=r(5082),l=r(4761),s=r(1379),i=r(3795),u=r(2384),c=r(399),d=r(7050),g=r(2483),m=r(5739),v=r(7474),f=r(7420),p=r(4909),h=r(65),b=r(8232),y=r(4231),Z=r(1037),C=r(6212);const W={class:"register-container"},I={class:"register-category-container"},w={class:"register-category-input-container"},F={class:"register-title-container"},_={class:"register-title-input-container"},H={class:"register-content-container"},T={class:"register-content-input-container"},V={class:"register-file-container"},q={class:"register-file-input-container"},S={name:"GalleryRegister"};var U=Object.assign(S,{setup(e){const a=(0,g.yj)(),r=(0,g.tv)(),S=(0,h.oR)(),U=(0,n.iH)({categoryId:"",boardTitle:"",boardContent:""}),M=(0,n.iH)({categoryId:"",boardTitle:"",boardContent:"",saveFiles:""}),$=(0,n.iH)([]),k=(0,n.iH)({});async function j(){k.value=(0,d.C)(a.query,3);try{$.value=await(0,v.A)(m.ui)}catch({message:e}){console.error(e)}}async function R(){if(!D()&&(0,C.$8)())return!1;try{const e=z();if(null===e.get("saveFiles"))return alert("이미지를 첨부해주세요."),!1;const a=await(0,b.HI)(e);S.commit("boardFileStore/clearFile"),r.push({path:`/galleries/${a}`,query:k.value})}catch({data:e,message:a}){for(const r in e)M.value[r]=e[r];console.error(a)}}function z(){const e=new FormData;for(const a in U.value)e.append(a,U.value[a]);return S.getters["boardFileStore/getSaveFiles"].forEach((a=>{e.append("saveFiles",a)})),e}function D(){M.value.boardTitle=(0,p.uq)(U.value.boardTitle),M.value.boardContent=(0,p.ty)(U.value.boardContent),M.value.categoryId=(0,p.Hm)(U.value.categoryId);for(const e of Object.values(M.value))if(e)return!1;return!0}return j(),(e,a)=>((0,t.wg)(),(0,t.iD)(t.HY,null,[(0,t.Wm)(y.Z),(0,t.Wm)(o.Z,{title:"갤러리"}),(0,t._)("div",W,[(0,t._)("div",I,[(0,t.Wm)(l.Z,{name:"분류",class:"board-form-title-category"}),(0,t._)("div",w,[(0,t.Wm)(u.Z,{modelValue:U.value.categoryId,"onUpdate:modelValue":a[0]||(a[0]=e=>U.value.categoryId=e),onChange:a[1]||(a[1]=e=>M.value.categoryId=(0,n.SU)(p.Hm)(U.value.categoryId)),"category-list":$.value,class:"register-category"},null,8,["modelValue","category-list"]),(0,t.Wm)(i.Z,{"error-msg":M.value.categoryId},null,8,["error-msg"])])]),(0,t._)("div",F,[(0,t.Wm)(l.Z,{name:"제목",class:"board-form-title-title"}),(0,t._)("div",_,[(0,t.Wm)(s.Z,{modelValue:U.value.boardTitle,"onUpdate:modelValue":a[2]||(a[2]=e=>U.value.boardTitle=e),onChange:a[3]||(a[3]=e=>M.value.boardTitle=(0,n.SU)(p.uq)(U.value.boardTitle)),placeholder:"제목을 입력해 주세요.",class:"register-title"},null,8,["modelValue"]),(0,t.Wm)(i.Z,{"error-msg":M.value.boardTitle},null,8,["error-msg"])])]),(0,t._)("div",H,[(0,t.Wm)(l.Z,{name:"내용",class:"board-form-title-content"}),(0,t._)("div",T,[(0,t.Wm)(c.Z,{modelValue:U.value.boardContent,"onUpdate:modelValue":a[4]||(a[4]=e=>U.value.boardContent=e),onChange:a[5]||(a[5]=e=>M.value.boardContent=(0,n.SU)(p.uq)(U.value.boardContent)),class:"register-content"},null,8,["modelValue"]),(0,t.Wm)(i.Z,{"error-msg":M.value.boardContent},null,8,["error-msg"])])]),(0,t._)("div",V,[(0,t.Wm)(l.Z,{required:!1,name:"갤러리 이미지",class:"board-form-title-file"}),(0,t._)("div",q,[(0,t.Wm)(f.Z),(0,t.Wm)(i.Z,{"error-msg":M.value.saveFiles},null,8,["error-msg"])])]),(0,t.Wm)(Z.Z,{onRegister:R,onCancel:a[6]||(a[6]=e=>(0,n.SU)(r).push({path:"/galleries",query:k.value})),"form-type":"register"})])],64))}}),M=r(89);const $=(0,M.Z)(U,[["__scopeId","data-v-0759f6c0"]]);var k=$}}]);
//# sourceMappingURL=717.70675c26.js.map